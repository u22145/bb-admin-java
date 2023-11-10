<template>
    <div class="multipleInput">
       <div class="Breadcrumbs"><span>公会管理</span><span>公会列表</span><span>編輯</span></div>
        <!-- input start -->
        <div class="information whiteBg">
            <el-form :label-position="labelPosition" label-width="28%" >
                <el-form-item label="公会名称：">
                    <el-input :disabled="true" v-model="guildName"  placeholder="公会名称"></el-input>
                </el-form-item>
                <el-form-item label="会长ID：">
                    <el-input :disabled="true" v-model="masterId" placeholder="1234567"></el-input>
                </el-form-item>
                <el-form-item label="礼物抽成：">
                    <el-input placeholder="请输入" v-model="giftCommission">
                            <template slot="append">%</template>
                    </el-input>
                </el-form-item>
                <el-form-item label="彩票盈利抽成：">
                    <el-input placeholder="请输入" v-model="lotteryCommission">
                        <template slot="append">%</template>
                    </el-input>
                </el-form-item>
                <el-form-item label="1V1视频抽成：">
                    <el-input placeholder="请输入" v-model="privateStreamCommission">
                        <template slot="append">%</template>
                    </el-input>
                </el-form-item>
                <el-form-item label="直播间门票抽成：">
                    <el-input placeholder="请输入" v-model="streamTicketCommission">
                        <template slot="append">%</template>
                    </el-input>
                </el-form-item>
                <el-form-item label="UGC抽成：">
                    <el-input placeholder="请输入" v-model="ugcCommission">
                        <template slot="append">%</template>
                    </el-input>
                </el-form-item>
                <el-form-item label="约会电台抽成：">
                    <el-input placeholder="请输入" v-model="datingCommission">
                        <template slot="append">%</template>
                    </el-input>
                </el-form-item>
                <el-form-item label="私信抽成：">
                    <el-input placeholder="请输入" v-model="messageCommission">
                        <template slot="append">%</template>
                    </el-input>
                </el-form-item>
                <el-form-item label="状态：" class="statusOption">
                    <el-select v-model="status" placeholder="请选择">
                        <el-option v-for="item in options" :key="item.value" :label="item.label" :value="item.value"></el-option>
                    </el-select>
                </el-form-item>
            </el-form>

        <!-- input end -->
        <!-- buttom start -->
        <el-row>
            <el-button @click="editSubmit" :plain="true" type="primary">提交</el-button>
            <el-button @click="goBack">取消</el-button>
            <el-button type="danger" :plain="true" @click="editDelet">刪除</el-button>
        </el-row>
        <!-- buttom end -->
        </div>
    </div> 
    
</template>
<script>
  export default {
    data() {
      return {
        labelPosition: 'right',
        options: [{
            value: 1,
            label: '正常'
            }, {
            value: 0,
            label: '解散'
        }],
        name: '',
        masterId: '',
        giftCommission: '',
        lotteryCommission: '',
        privateStreamCommission: '',
        streamTicketCommission: '',
        ugcCommission: '',
        datingCommission: '',
        messageCommission: '',
        guildId: '',
        status: ''
      };
    },
    beforeMount() {
        this.masterId = this.$route.query.id
        this.guildName = this.$route.query.name
        this.guildId = this.$route.query.guildId
    },
    mounted() {
        this.getCurrentGuildData()
    },
    methods: {
        getCurrentGuildData() {
            this.$axios.get('guild', {
                params: {
                    guild: this.guildId,
                }
            })
            .then(function (response) {
                    // set default value of edit
                    // console.log(response)
                    let data = response.data.data.data[0]
                    this.giftCommission = data.giftCommission * 100
                    this.lotteryCommission = data.lotteryCommission * 100
                    this.privateStreamCommission = data.privateStreamCommission * 100
                    this.streamTicketCommission = data.streamTicketCommission * 100
                    this.ugcCommission = data.ugcCommission * 100
                    this.datingCommission = data.datingCommission * 100
                    this.messageCommission = data.messageCommission * 100
                    this.status = data.status
            }.bind(this))
            .catch(function (error) {
                console.log('请求失败的返回值');
            });
        },
        editSubmit() {
            this.$axios.put('guild/', {
                id: this.guildId,
                giftCommission: this.giftCommission / 100,
                lotteryCommission: this.lotteryCommission / 100,
                privateStreamCommission: this.privateStreamCommission / 100,
                streamTicketCommission: this.streamTicketCommission / 100,
                ugcCommission: this.ugcCommission / 100,
                datingCommission: this.datingCommission / 100,
                messageCommission: this.messageCommission / 100,
                status: this.status
            })
            .then(function (response) {
                console.log(response);
                if(response.data.status == 1 && response.data.data =="OK" && response.data.msg =="") {
                     // show edit guild success
                     console.log("成功");
                    this.$message({
                        message: '提交成功',
                        type: 'success'
                    });
                    setTimeout(function(){ 
                        this.$router.push('/guildList'); }.bind(this), 1000);
                }else{
                    console.log("北七唷");
                    this.$message.error('输入值须介于0～1之间');
                }
            }.bind(this))
            .catch(function (error) {
                console.log(error);
            });
        },
        goBack() {
            this.$router.go(-1)
        },
        editDelet() {
            this.$confirm('删除后该公会抽成将无法正常计算，操作不可逆，你还要继续吗？', '删除公会', {
            confirmButtonText: '确定删除',
            cancelButtonText: '取消删除',
            type: 'warning'
        }).then(() => {
            this.$message({
            type: 'success',
            message: '删除成功!'
            });
            setTimeout(function(){ 
                this.$router.push('/guildList'); }.bind(this), 1500);
        }).catch(() => {
            this.$message({
            type: 'info',
            message: '已取消删除'
            });   
            setTimeout(function(){ 
                this.$router.push('/guildList'); }.bind(this), 1500);     
        });
      }
    }
  }
</script>