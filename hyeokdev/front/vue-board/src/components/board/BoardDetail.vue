<template>
  <div class="container">
    <div class="bg-success">
      <h2>{{ article.subject }}</h2>
      <p>
        <span class="glyphicon glyphicon-user"></span>
        {{ article.writer }}
        <span class="glyphicon glyphicon-calendar"></span>
        {{ article.regdate }}
      </p>
    </div>
    <div class="article-body">
      {{article.contents}}
    </div>
    <div class="article-file">
      <ul class="list-unstyled">
        <li class="list-item" v-for="(file, idx) in article.fileList" v-bind:key="idx">
          <button v-on:click="fileDownload(file.fileId)">
            <span class="glyphicon glyphicon-floppy-disk"/>
            {{file.orgNm}}
          </button>
        </li>
      </ul>
    </div>
    <div class="pull-left" v-if="isMine">
        <button v-on:click="updateForm()" class="btn btn-success">수정</button>
        <button v-on:click="deleteArticle()" class="btn btn-danger">삭제</button>
    </div>
    <div class="pull-right">
        <router-link to="/board" class="btn btn-default">목록</router-link>
    </div>
  </div>
</template>

<script>
export default {
  props: ['boardId'],
  data: function() {
      return {
        article: {
          userId: null,
          subject: '',
          contents: '',
          writer: '',
          regdate: '',
          fileList: []
        },
        sessionId: '',
        isMine: false
      }
  },
  methods: {
    loadArticle: function() {
      axios.get('/api/board/'+this.boardId, {headers: {'Authorization': 'Bearer ' + this.$cookie.get('access_token')}})
      .then((response) => {
        this.article = response.data
        if(this.article.userId == this.sessionId) {
          this.isMine = true
        }
      }, (error) => {
        consol.log(error)
      })  
    },
    deleteArticle: function() {
      axios.delete('/api/board/'+ this.article.articleId,
      {
        headers: {
          'Authorization': 'Bearer ' + this.$cookie.get('access_token')
        }
      }
      )
      .then((response) => {
          this.$router.push('/board')
      }, (error) => {
        alert('삭제 오류 입니다.')
      })
    },
    updateForm: function() {
      this.$router.push('/board/'+this.boardId+'/update')
    },
    fileDownload: function(fileId) {
      axios.get('/api/board/'+fileId+'/download', 
      {
        headers: {
          'Authorization': 'Bearer ' + this.$cookie.get('access_token')
        }
      }).then((response) => {
        console.log('성공')
      }).catch((exeption) => {
        console.warn('실패')
      })
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
            this.sessionId = response.data.user_id
        }).catch((exception) => {
            console.warn(exception)
        })
    this.loadArticle()
  }
}
</script>

<style scoped>
  p {
    text-align: right;
  }
  .article-body {
    text-align: left;
  }
  .list-item {
    text-align: left
  }
  .list-item button {
    border: none;
    background-color: white;
  }
  .list-item button:hover {
    background-color: greenyellow;
  }
  input {
    width: 170px;
  }
</style>