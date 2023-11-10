<template>
    <div>
       <h2>管理員登入</h2>
       <div class="login">
           <el-form :label-position="labelPosition" label-width="80px">
                <el-form-item label="帳號" class="account">
                    <el-input v-model="account"  placeholder="请输入帳號"></el-input>
                </el-form-item>
                <el-form-item label="密码" class="password">
                    <el-input v-model="pass" type="password" placeholder="请输入密码"></el-input>
                </el-form-item>
                <el-button @click="submit" type="primary">管理員登入</el-button>
            </el-form>
       </div>
    </div>
</template>
<script>
  export default {
    data() {
      return {
        labelPosition: 'right',
        account: '',
        pass: ''
      };
    },
    mounted() {
      console.log("123")
    },
    methods: {
      submit() {
        this.$axios.post('gm/login', {
          "account": this.account,
          "password": this.pass
        })
        .then(res => {
          if(res.data.status == 1) {
            this.$router.push('/')
          }
        })
      }
    }
  }
</script>