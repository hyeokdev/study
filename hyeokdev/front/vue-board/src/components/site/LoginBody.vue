<template>
    <div class="container form-horizontal">
        <div class="form-group">
            <label for="inputEmail3" class="col-sm-2 control-label">아이디</label>
            <div class="col-sm-10">
            <input type="text" v-model="userName" class="form-control" placeholder="아이디를 입력하세요.">
            </div>
        </div>
        <div class="form-group">
            <label for="inputPassword3" class="col-sm-2 control-label">비밀번호</label>
            <div class="col-sm-10">
            <input type="password" v-model="userPassword" class="form-control"  placeholder="비밀번호를 입력하세요.">
            </div>
        </div>
        <div class="form-group">
            <div class="col-sm-5">
            <button v-on:click="login()" class="btn btn-success">로그인</button>
            </div>
        </div>
        <div class="form-group col-sm-6">
            <router-link to="/join">아직 회원이 아니신가요?</router-link>
        </div>
    </div>
</template>

<script>
export default {
    data: function() {
        return {
            userName : '',
            userPassword: '',
            isLogined: false
        }
    },
    methods: {
        login: function() {
            axios.get('/api/user/login', {
                params: {
                    'userName': this.userName,
                    'userPassword': this.userPassword
                }
            }).then((response) => {
                this.$cookie.set('access_token', response.data.access_token, {expires: '1h'})
                this.isLogined = true
                this.EventBus.$emit("login-eventbus", this.isLogined)
                this.$router.push('/');
            }).catch((exception) => {
                console.warn("LOGIN ERROR" + exception)
            })
        }
    }
}
</script>

<style scoped>
    .form-control {
        width: 200px;
    }
</style>