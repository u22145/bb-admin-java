<template>
    <el-row :span="24">
        <div class="head-wrap">後臺管理系統 </div>
        <!-- <div class="admin">
            <el-popover
            placement="bottom"
            title=""
            width="100"
            trigger="click"
            content="">
                <el-button slot="reference">admin<i class="el-icon-arrow-down"></i></el-button>
                <el-button type="text" @click="dialogVisible = true">修改密碼</el-button>
                <el-button type="text" @click="dialogVisible12 = true">登出</el-button>
            </el-popover>
        </div> -->
        
        <el-dialog
        title="修改密碼"
        :visible.sync="dialogVisible"
        width="30%"
        :before-close="handleClose">
        <el-form :label-position="labelPosition" label-width="80px" >
            <el-form-item label="原密码">
                <el-input v-model="formLabelAlign.name"></el-input>
            </el-form-item>
            <el-form-item label="新密码">
                <el-input v-model="formLabelAlign.region"></el-input>
            </el-form-item>
            <el-form-item label="确认密码">
                <el-input v-model="formLabelAlign.type"></el-input>
            </el-form-item>
        </el-form>
    <span slot="footer" class="dialog-footer">
        <el-button @click="dialogVisible = false">取 消</el-button>
        <el-button type="primary" @click="dialogVisible = false">确 定</el-button>
    </span>
    </el-dialog>
    </el-row>
</template>
<script>
  export default {
    data() {
      return {
        dialogVisible: false,
        visible: false,labelPosition: 'right',
        formLabelAlign: {
          name: '',
          region: '',
          type: ''
        }
      };
    },
    methods: {
      handleClose(done) {
        this.$confirm('确认关闭？')
          .then(_ => {
            done();
          })
          .catch(_ => {});
      }
    }
  };
</script>