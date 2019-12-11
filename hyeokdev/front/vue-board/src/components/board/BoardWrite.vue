<template>
    <div class="container">
        <form class="form-horizontal" @submit.prevent="checkForm">
            <div class="form-group">
                <label for="subject" class="col-sm-2 control-label">제목</label>
                <div class="col-sm-10">
                    <input type="text" v-model="subject" class="form-control" name="subeject" id="subject" placeholder="제목을 입력하세요.">
                </div>
            </div>
            <div class="form-group">
                <label for="contents" class="col-sm-2 control-label">내용</label>
                <div class="col-sm-10">
                    <textarea v-model="contents" class="form-control" name="contents" id="contents" placeholder="내용을 입력하세요." rows="5"/>
                </div>
            </div>
            <div class="form-group">
                <label for="writer" class="col-sm-2 control-label">작성자</label>
                <div class="col-sm-10">
                    <input type="text" v-model="writer" class="form-control" name="writer" id="writer" readonly>
                </div>
            </div>
            <div class="form-group">
                <label for="files" class="col-sm-2 control-label">첨부파일</label>
                <div class="col-sm-10">
                    <input type="file" ref="files" name="files" id="files" multiple v-on:change="handleFileUpload()">
                </div>
            </div>
            <div class="pull-right">
                <button type="submit" class="btn btn-success">작성</button>
                <router-link to="/board" class="btn btn-default">취소</router-link>
            </div>
        </form>
    </div>
</template>

<script>
export default {
    data: function() {
        return {
            subject: null,
            contents: null,
            writer: null,
            userId: null,
            files: ''
        }
    },
    mounted: function() {
        axios.get('/api/user/info',
        {
            params: {
                'accessToken': this.$cookie.get('access_token')
            },
            headers: {
                'Authorization': 'Bearer ' + this.$cookie.get('access_token')
            }
        })
        .then((response) => {
            this.writer = response.data.user_nickname
            this.userId = response.data.user_id
        }).catch((exception) => {
            console.warn(exception)
        })
    },
    methods: {
        checkForm: function() {
            if(!this.subject) {
                alert('제목을 입력해주세요.');
                return false;
            }
            if(!this.contents) {
                alert('내용을 입력해주세요.');
                return false;
            }
            if(!this.writer) {
                alert('작성자를 입력해주세요.');
                return false;
            }
            this.postArticle()
        },
        postArticle: function() {
            let formData = new FormData();
            for(var i=0; i<this.files.length; i++) {
                let file = this.files[i];
                formData.append('files', file);
            }
            axios.post('/api/board/post', {
                subject: this.subject,
                contents: this.contents,
                writer: this.writer,
                userId: this.userId
            },
            {
                headers: {
                    'Authorization': 'Bearer ' + this.$cookie.get('access_token')
                }
            }
            ).then((response) => {
                this.postFile(response.data.articleId, formData)
             }).catch((exception) => {
                 console.warn("ERROR!!! : " + exception)
             })
        },
        handleFileUpload: function() {
            this.files = this.$refs.files.files;
        },
        postFile: function(boardId, formData) {
            axios.post('/api/board/'+boardId+'/files', formData, 
            {
                headers: {
                    'Content-Type': 'multipart/form-data',
                }
            }).then((res) => {
                this.$router.push('/board');
            }).catch((ex) => {
                console.warn('fail file upload')
            })
        }
    }
}
</script>

<style>

</style>