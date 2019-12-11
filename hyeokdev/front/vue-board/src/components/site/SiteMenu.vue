<template>
    <div class="container-fluid">
        <div class="sitemenu">
            <div class="panel panel-info">
                <div class="panel-heading">
                    <h3 class="panel-title">메뉴</h3>
                </div>
                <ul class="nav nav-pills nav-stacked">
                    <li role="presentation">
                        <router-link to="/">HOME</router-link>
                    </li>
                    <li role="presentation">
                        <router-link to="/board">게시판</router-link>
                    </li>
                    <li role="presentation">준비중</li>
                    <li role="presentation">준비중</li>
                </ul>
            </div>
        </div>
    </div>
</template>

<script>
export default {
    name: "SiteMenu"
}
</script>

<style scopes>
    .sitemenu {
        position:absolute;
        float: left;
        width: 150px;
        height: 100%;
    }
    .panel-info .panel-heading {
        background-color: yellowgreen;
    }
</style>