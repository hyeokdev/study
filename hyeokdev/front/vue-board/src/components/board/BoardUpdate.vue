<template>
  <div class="container">
        <div class="form-group">
            <label for="subject" class="col-sm-2 control-label">제목</label>
            <div class="col-sm-10">
                <input type="text" class="form-control" name="subeject" id="subject" placeholder="제목을 입력하세요." v-model="article.subject">
            </div>
        </div>
        <div class="form-group">
            <label for="contents" class="col-sm-2 control-label">내용</label>
            <div class="col-sm-10">
                <textarea class="form-control" name="contents" id="contents" placeholder="내용을 입력하세요." rows="5" v-model="article.contents"/>
            </div>
        </div>
        <div class="form-group">
            <label for="writer" class="col-sm-2 control-label">작성자</label>
            <div class="col-sm-10">
                <input type="text" class="form-control" name="writer" id="writer" readonly v-model="article.writer">
            </div>
        </div>
        <div class="pull-right">
            <button v-on:click="updateArticle()" class="btn btn-success">수정</button>
            <button v-on:click="backArticle()" class="btn btn-default">취소</button>
        </div>
  </div>
</template>

<script>
export default {
    props: ['boardId'],
    data: function() {
      return {
        article: {
            subject: '',
            contents: '',
            writer: ''
        },
      }
    },
    methods: {
        loadArticle: function() {
            axios.get('/api/board/'+this.boardId, 
            {
                headers: {
                    'Authorization': 'Bearer ' + this.$cookie.get('access_token')
                }
            })
            .then((response) => {
                this.article = response.data;
            }, (error) => {
                console.log(error)
            })
        },
        updateArticle: function() {
            axios.put('/api/board/'+this.boardId+'/update', this.article,
            {
                headers: {
                    'Authorization': 'Bearer ' + this.$cookie.get('access_token')
                }
            }
            ).then((response) => {
                this.$router.push('/board/'+this.boardId);    
            }, (error) => {
                console.log(error)
            })
        },
        backArticle: function() {
            this.$router.push('/board/'+this.boardId);
        }
    },
    mounted: function() {
        this.loadArticle()
    }

}
</script>

<style>

</style>