<template>
  <div class="container">
    <table class="table table-hover">
        <thead>
            <tr>
                <th style="width:5%">번호</th>
                <th style="width:55%">제목</th>
                <th>작성자</th>
                <th>파일</th>
                <th>등록일</th>
            </tr>
        </thead>
        <tbody>
            <tr v-for="(article, idx) in articles" :key="idx" v-on:click="articleDetail(article.articleId)">
                <td>{{ article.articleId }}</td>
                <td>{{ article.subject }}</td>
                <td>{{ article.writer }}</td>
                <td><span v-if="article.fileList.length > 0" class="glyphicon glyphicon-floppy-disk"></span></td>
                <td>{{ article.regdate}}</td>
            </tr>
            <tr v-if="articles.length<1">
                <td colspan="5">등록된 게시물이 없습니다.</td>
            </tr>
        </tbody>
    </table>
    <div class="pull-right">
    <router-link to="/board/post" class="btn btn-success">글쓰기</router-link>
    </div>
    <paginate :page-count="totalpage" :prev-text="'Prev'" :next-text="'Next'" :container-class="'pagination'" :margin-pages="1" :page-range="9" :click-handler="goPage">
    </paginate>
  </div>
</template>

<script>
import paginate from 'vuejs-paginate'

export default {
    components: {
        'paginate': paginate
    },
    data: function() {
        return {
            articles: [],
            page: 0,
            totalpage: 0,
            isActive: ''
        };
    },
    methods: {
        fetchArticles: function() {
            if(this.$cookie.get('access_token') == null) {
                alert('서비스를 이용하시려면 로그인이 필요합니다.')
                this.$router.push('/login')
            } else {
                axios.get('/api/board',
                {
                    params: {
                        'page': this.page,
                        'size': 10,
                        'sort': 'articleId,desc' 
                    },
                    headers: {
                        'Authorization': 'Bearer ' + this.$cookie.get('access_token')
                    }
                }
                ).then((response) => {
                    this.articles = response.data.content
                    this.totalpage = response.data.totalPages
                    if(this.page==response.data.number) {
                        this.isActive=true
                    } else {
                        this.isActive=false
                    }
                }, (error) => {
                    console.log(error)
                })
            }
        },
        articleDetail: function(idx) {
            this.$router.push({name: 'boardDetail', params: {boardId: idx}})
        },
        goPage: function(page) {
            this.page = page-1
        }
    },
    mounted: function() {
        this.fetchArticles()
    },
    watch: {
        page: function() {
            this.fetchArticles()
        }
    }
}
</script>

<style scoped>
    thead th {
        text-align: center;
    }
    .active {
        background-color: red;
    }
</style>