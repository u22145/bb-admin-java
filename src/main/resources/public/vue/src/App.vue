<template>
  <div id="app">
    <el-container>
      <el-header class="header"><vheader /></el-header>
      <el-container>
        <el-aside width='250px' v-if="$route.name != 'login'">
          <navmenu :type="type"></navmenu>  
        </el-aside>
        <el-main>
          <router-view></router-view>
        </el-main>
      </el-container>
    </el-container>
    <!-- <router-view/> -->
  </div>
</template>

<script>
require('@/assets/styles/main.css')
import NavMenu from '@/components/NavMenu'
import Header from '@/components/Header'
export default {
  name: 'App',
  components: {
    'navmenu': NavMenu,
    'vheader': Header
  },
  data() {
    return {
      type: 0
    }
  },
  mounted() {
    console.log(process.env.NODE_ENV)
  }
}
</script>

<style>
.rightGroup .el-table .cell,
.FG span,
.leftGroup p{
  font-size: 13px;
}
#app {
  font-family: 'Avenir', Helvetica, Arial, sans-serif;
  -webkit-font-smoothing: antialiased;
  -moz-osx-font-smoothing: grayscale;
  text-align: center;
  color: #2c3e50;
}
body{
  background-color: #F8F8F8;
}
.whiteBg{
  background-color: #ffffff;
  padding: 20px;
  border-radius: 5px;
  margin-top: 20px;
}
.header {
  background: #409EFF;
  color: #fff;
  line-height: 60px;
}
.statusOption .el-select{
  width: 100%;
}
.multipleInput>div,
.multipleInput>h2{
  width: 500px;
}
.borderStyle{
  border-width: 1px;
  border-style: solid;
  border-color: rgb(235, 235, 235);
  border-radius: 4px;
  margin:0 4px;
}
.guildReport~.el-row,
.screen .el-form-item.mb20{
  margin-bottom: 20px;
}
.fiveGroup{
  margin-bottom: 20px;
}
.fiveGroup>div:first-child,
.leftGroup{
  margin-left: 0;
}
.fiveGroup>div:last-child,
.rightGroup{
  margin-right: 0;
}
.FG{
  width: 45%;
  padding: 9px 16px;
}
.FG span{
  text-align: left;
  display: block;
  color: #888;
}
.FG strong{
  font-size: 30px;
  text-align: left;
  display: block;
}
.leftGroup,
.rightGroup{
  padding: 10px;
}
.leftGroup{
  position: relative;
  width: 66%;
}
.leftGroup p{
  text-align: left;
  margin: 30px 0;
}
.leftGroup p strong{
  width: 30%;
  display: inline-block;
  text-align: right;
}
.rightGroup .el-table td{
  padding: 7px 0;
}
.details{
  position: absolute;
  right: 15px;
  top: 15px;
    
}
.guildOwnerIncome .userIcon strong{
  padding-top: 5px;
  border-right: 1px solid #e4e4e4;
}
.guildOwnerIncome .userIcon i{
  padding: 10px;
  background-color: #409EFF;
  border-radius: 50%;
  margin-right: 10px;
  color:#ffffff;
}
.blockStyle{
  box-shadow: 2px 0px 7px 0px #ABABAB;
  -webkit-box-shadow: 2px 0px 7px 0px #ABABAB;
  -moz-box-shadow: 2px 0px 7px 0px #ABABAB;
  -o-box-shadow: 2px 0px 7px 0px #ABABAB;
  border-radius: 5px;
  padding: 1px 0 15px 0;
  margin-top: 20px;
}
.w60{
  width: 60%;
}
.Breadcrumbs{
  text-align: left;
  font-size: 13px;
  color: #888;
}
.Breadcrumbs span+span::before{
  content:">";
  display: inline-block;
  margin: 0 10px;
}
.Breadcrumbs>span:last-child{
  color:#419efb;
  font-weight: bold;
}
.Breadcrumbs>span:last-child::before{
  color: #888;
  font-weight: 400;
}
.el-main{
  position: relative;}
.backTo{
  position: absolute;
  right: 0;
  top: 9px;
}
.screen .el-form-item{
  margin-bottom: 0px;
}
.screen{
  box-shadow: 2px 0px 7px 0px #ABABAB;
  -webkit-box-shadow: 2px 0px 7px 0px #ABABAB;
  -moz-box-shadow: 2px 0px 7px 0px #ABABAB;
  -o-box-shadow: 2px 0px 7px 0px #ABABAB;
}
.information{
  padding-bottom: 20px;
}
.pagination{
  margin-top: 20px;
}
.el-menu.el-menu--inline{
  background-color: hsl(0 0% 94% / 1);
}
.el-menu.el-menu--inline .el-menu.el-menu--inline{
   background-color: hsl(0 0% 90% / 1);
}
.el-submenu,
.el-menu-item{
  border-top: 1px solid hsl(0deg 0% 83% / 43%);
  text-align: left;
}
.head-wrap{
  font-size: 30px;
  letter-spacing: 4px;
}
.el-menu-vertical-demo>.el-submenu>.el-submenu__title{
  padding-left: 10px!important;
}
.admin{
  position: absolute;
  right: 0;
  top: 0;
}
.admin .el-button{
  background: #fff0;
  border: 1px solid #dcdfe600;
  color:#fff;
}
.admin .el-button:focus,
.admin .el-button:hover{
  background: #fff;
  color:#419efb;
}
.el-message-box__message,
.deleteBT.el-button--text{
  color: hsl(0deg 100% 50% / 74%);
}
.deleteBT.el-button--text:focus, 
.deleteBT.el-button--text:hover{
  color: hsl(0 100% 67% / 0.74);
}
.login{
  width: 70%;
  margin: 0 auto;
}
.pagePosition{
  text-align: left;
  color: #ababab;
    font-size: 14px;
}
.pagination.el-pagination{
  text-align: right;
}
.profitStatus tbody .el-table_2_column_22,
.profitStatus tbody .el-table_2_column_24   {
  color: rgba(0, 128, 0, 0.647058823529412);
}
</style>
