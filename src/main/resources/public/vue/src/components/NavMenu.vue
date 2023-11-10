<template>
    <el-row>
  <el-col :span="24">
    <el-menu
      default-active="1"
      class="el-menu-vertical-demo"
      @open="handleOpen"
      @close="handleClose"
      router
      :unique-opened=true
      active-text-color="#409EFF">
      <el-submenu index="1" v-if="this.type == 0">
        <template slot="title">
          <i class="el-icon-location"></i>
          <span>公会管理</span>
        </template>
        <el-menu-item index="/guildList">公会列表</el-menu-item>
        <!-- <el-submenu>
          <template slot="title"  index="/guildList">公会列表</template>
          <el-menu-item index="/createGuild">新建</el-menu-item> 
          <el-menu-item index="/guildList">檢視</el-menu-item> 
        </el-submenu>-->
        <el-submenu index="1-1">
          <template slot="title">公会数据报表</template>
          <el-menu-item index="/guildReport">檢視</el-menu-item>
          <el-menu-item index="/dataDetail">总数据详情</el-menu-item>
          <!--<el-submenu index="1-1-1">
              <template slot="title" index="/dataDetail">总数据详情</template>
               <el-menu-item index="/dataDetail">檢視</el-menu-item>
              <el-menu-item index="/streamHome">直播间详情</el-menu-item>
              <el-menu-item index="/oneOnoneHome">1V1视频详情</el-menu-item>
              <el-menu-item index="/ugcDetail">UGC收益详情</el-menu-item>
              <el-menu-item index="/dateIncome">约会电台收益详情</el-menu-item>
              <el-menu-item index="/privateIncome">私信收益详情</el-menu-item> 
          </el-submenu>-->
          <el-submenu index="1-1-2">
              <template slot="title">公会贡献</template>
            <el-menu-item index="/guildContribution">檢視</el-menu-item>
            <el-menu-item index="/anchorContribution">主播贡献</el-menu-item>
          </el-submenu>
          <!-- <el-menu-item index="1-5-1">选项1</el-menu-item> -->
        </el-submenu>
        <el-menu-item index="/rakeReport">抽成结算报表</el-menu-item>
      </el-submenu>
      <el-submenu index="2" v-if="this.type == 1">
        <template slot="title">
          <i class="el-icon-location"></i>
          <span>公会管理系统</span>
        </template>
        <el-submenu index="2-1">
          <template slot="title">公会管理</template>
          <!--<el-submenu index="2-1-1">
              <template slot='title' index="/guildOwnerMemberManage">成员列表</template>
              <el-menu-item index="/guildOwnerMemberManage">檢視</el-menu-item>
              <el-menu-item index="/guildOwnerInvite">邀请</el-menu-item>
              <el-menu-item index="/guildOwnerEdit">编辑</el-menu-item> 
          </el-submenu>-->
          <el-menu-item index="/guildOwnerMemberManage">成员列表</el-menu-item>
          <el-menu-item index="/performance">绩效考核</el-menu-item>
        </el-submenu>
        <el-submenu index="2-2">
          <template slot="title">报表管理</template>
          <el-menu-item index="/guildOwnerStreamReport">直播间报表</el-menu-item>
          <!-- <el-submenu index="2-2-1">
              <template slot="title">直播间报表</template>
              <el-menu-item index="/guildOwnerStreamReport">檢視</el-menu-item>
          </el-submenu> -->
          <el-menu-item index="/guildOwnerOneOnOneReport">1V1视频报表</el-menu-item>
          <!-- <el-submenu index="2-2-2">
              <template slot="title">1V1视频报表</template>
              <el-menu-item index="/guildOwnerOneOnOneReport">檢視</el-menu-item>
          </el-submenu> -->
          <el-menu-item index="/guildOwnerUGCReport">UGC报表</el-menu-item>
          <el-menu-item index="/guildOwnerDateReport">约会电台报表</el-menu-item>
          <el-menu-item index="/guildOwnerPrivate">私信收益报表</el-menu-item>
          <el-menu-item index="/guildOwnerIncome">收益报表</el-menu-item>
        </el-submenu>
      </el-submenu>
    </el-menu>
  </el-col>
</el-row>
</template>
<script>
  import menu from '@/config/menu-config'
  export default {
      data() {
          return {
              menu: menu
          }
      },
      props: ['type'],
    methods: {
      handleOpen(key, keyPath) {
        console.log(key, keyPath);
      },
      handleClose(key, keyPath) {
        console.log(key, keyPath);
      }
    },
    mounted() {
      console.log(this.type)
    }
  }
</script>