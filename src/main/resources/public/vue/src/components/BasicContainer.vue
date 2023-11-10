<template>
    <div>
        <el-form :inline="true" :model="tableData" class="demo-form-inline">
            <el-form-item label="公会ID：">
                <el-input v-model="tableData.id" placeholder="请输入"></el-input>
            </el-form-item>
            <el-form-item label="会长D：">
                <el-input v-model="tableData.masterId" placeholder="请输入"></el-input>
            </el-form-item>
            <el-form-item label="状态：">
                <el-select v-model="tableData.updatedAt" placeholder="全部">
                <el-option label="正常" value="shanghai"></el-option>
                <el-option label="禁止" value="beijing"></el-option>
                </el-select>
            </el-form-item>
            <el-form-item>
                <el-button type="primary" @click="onSubmit">搜索</el-button>
                <el-button @click="resetForm('formInline')">重置</el-button>
                <el-button type="primary" @click="submitForm('formInline')">新建</el-button>
            </el-form-item>
        </el-form>
        <el-table :data="tableData" style="width: 100%">
        <el-table-column prop="createdAt" label="新建时间" width="180"></el-table-column>
        <el-table-column prop="name" label="公会名称" width="180"></el-table-column>
        <el-table-column prop="id" label="公会ID" width="180"></el-table-column>
        <el-table-column prop="masterId" label="会长ID" width="180"></el-table-column>
        <el-table-column prop="" label="状态"></el-table-column>
        <el-table-column prop="" label="操作"></el-table-column>
        </el-table>
        <el-pagination layout="prev, pager, next" :total="50"></el-pagination>
    </div> 
    
</template>
<script>
export default {
    data() {
        return {
            tableData: null,
            formInline: {
            user: '',
            region: ''
            }
        }
    },
    mounted() {
        // 向具有指定ID的用户发出请求
        this.$axios.get('guild/')
        .then(function (response) {
            console.log(this)
            console.log(response.data.data.data)
            this.tableData = response.data.data.data
        }.bind(this))
        .catch(function (error) {
            console.log('请求失败的返回值');
        });
    },
    methods: {
      onSubmit() {
        console.log('submit!');
      },
      submitForm(formName) {
        this.$refs[formName].validate((valid) => {
          if (valid) {
            alert('submit!');
          } else {
            console.log('error submit!!');
            return false;
          }
        });
      },
      resetForm(formName) {
        this.$refs[formName].resetFields();
      }
    }
}
</script>