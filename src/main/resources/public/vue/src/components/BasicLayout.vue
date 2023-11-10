<template>
    <el-table
      :data="data"
      style="width: 100%">
      <el-table-column
        prop="id"
        label="公會ID"
        width="180">
      </el-table-column>
      <el-table-column
        prop="name"
        label="公會名稱"
        width="180">
      </el-table-column>
      <el-table-column
        prop="masterId"
        label="會長ID">
      </el-table-column>
    </el-table>
</template>
<script>
export default {
    data() {
        return {
            data: null
        }
    },
    mounted() {
        // 向具有指定ID的用户发出请求
        this.$axios.get('guild/')
        .then(function (response) {
            console.log(this)
            console.log(response.data.data.data)
            this.data = response.data.data.data
        }.bind(this))
        .catch(function (error) {
            console.log('请求失败的返回值');
        });
    }
}
</script>