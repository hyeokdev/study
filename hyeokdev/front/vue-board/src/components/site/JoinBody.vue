<template>
    <div class="container form-horizontal">
        <div class="form-group">
            <label for="inputEmail3" class="col-sm-2 control-label">아이디</label>
            <div class="col-sm-10">
            <input type="text" class="form-control" placeholder="아이디를 입력하세요." v-model="userName">
            </div>
        </div>
        <div class="form-group">
            <label for="inputPassword3" class="col-sm-2 control-label">비밀번호</label>
            <div class="col-sm-10">
            <input type="password" class="form-control" placeholder="비밀번호를 입력하세요." v-model="userPassword">
            </div>
        </div>
        <div class="form-group">
            <label for="inputPassword3" class="col-sm-2 control-label">비밀번호 확인</label>
            <div class="col-sm-10">
            <input type="password" class="form-control" placeholder="비밀번호를 입력하세요.">
            </div>
        </div>
        <div class="form-group">
            <label for="inputEmail3" class="col-sm-2 control-label">닉네임</label>
            <div class="col-sm-10"> 
            <input type="text" class="form-control" placeholder="닉네임을 입력하세요." v-model="userNickname">
            </div>
        </div>
        <div class="form-group">
            <button v-on:click="joinUser()" class="btn btn-success">가입</button>
            <router-link to="/login" class="btn btn-default">취소</router-link>
        </div>
    </div>
</template>

<script>
export default {
    data: function() {
        return {
            userName: '',
            userPassword: '',
            userNickname: ''
        }
    },
    methods: {
        joinUser: function() {
            axios.post("/api/user/post", {
                userName: this.userName,
                userPassword: this.userPassword,
                userNickname: this.userNickname
            }).then((response) => {
                console.log(response.data)
                alert('환영합니다! ' + this.userName)
                this.$router.push('/login')
            }).catch((exception) => {
                console.warn("ERROR : " + exception)
            })       
        }
    }
}
</script>

<style>
    .form-control {
        width: 200px;
    }
</style>