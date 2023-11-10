<template>
    <div class="multipleInput">
        <div class="Breadcrumbs"><span>公会管理系统</span><span>公会管理</span><span>成员列表</span><span>编辑成员 API未完成</span></div>
        <!-- input start -->
        <div class="information whiteBg">
            <el-form :label-position="labelPosition" label-width="28%"  >
                <el-form-item label="主播ID：">
                    <el-input :disabled="true" v-model="uid" placeholder="请输入"></el-input>
                </el-form-item>
                <el-form-item label="绩效指标：">
                    <el-input v-model="performance" placeholder="请输入"></el-input>
                </el-form-item>
                <el-form-item label="达标次数：">
                    <el-input :disabled="true" placeholder="请输入" v-model="goalCount">
                            <!-- <template slot="append">%</template> -->
                    </el-input>
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
                <el-form-item label="约会电台抽成：">
                    <el-input placeholder="请输入" v-model="datingCommission">
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
                <el-button plain @click="submit" type="primary">提交</el-button>
                <el-button @click="goBack">取消</el-button>
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
            label: '禁止'
        }],
        id: 1,
        uid: '',
        performance: "",
        goalCount: 1,
        giftCommission: "",
        lotteryCommission: "",
        privateStreamCommission: "",
        streamTicketCommission: "",
        ugcCommission: "",
        datingCommission: "",
        messageCommission: "",
        status: "",
        tierId: ""
      };
    },
    methods: {
        getMemberdata() {
            this.$axios.get('gm/anchor/', {
                params: {
                    uid: this.uid,
                }
            })
            .then(function (response) {
                let data = response.data.data.data[0]
                this.performance = data.performance
                this.giftCommission = data.giftCommission*100
                this.lotteryCommission = data.lotteryCommission*100
                this.privateStreamCommission = data.privateStreamCommission*100
                this.streamTicketCommission = data.streamTicketCommission*100
                this.ugcCommission = data.ugcCommission*100
                this.datingCommission = data.datingCommission*100
                this.messageCommission = data.messageCommission*100
                this.status = data.status
                this.tierId = data.tierId
            }.bind(this))
            .catch(function (error) {
                console.log('请求失败的返回值');
            });
        },
        submit() {
            this.$axios.put('gm/anchor', {
                uid: this.uid,
                performance: this.performance,
                anchorTiers: [{
                    id: this.tierId,
                    goalCount: this.goalCount,
                    giftCommission: parseInt(this.giftCommission)/100,
                    lotteryCommission: parseInt(this.lotteryCommission)/100,
                    privateStreamCommission: parseInt(this.privateStreamCommission)/100,
                    streamTicketCommission: parseInt(this.streamTicketCommission)/100,
                    ugcCommission: parseInt(this.ugcCommission)/100,
                    datingCommission: parseInt(this.datingCommission)/100,
                    messageCommission: parseInt(this.messageCommission)/100,
                    status: this.status
                }]
            })
            .then(function (response) {
                console.log(response);
                if(response.data.status == 1) {
                     // show edit guild success
                     console.log("成功");
                     this.$notify({
                        title: '成功',
                        message: '提交成功',
                        type: 'success',
                        duration: 2000
                    });
                    setTimeout(function(){ 
                        this.$router.push('/guildOwnerMemberManage'); }.bind(this), 1500); 
                }
            }.bind(this))
            .catch(function (error) {
                console.log(error);
            });
            
        },
        goBack() {
            this.$router.push('/guildOwnerMemberManage')
        },
    },
    beforeMount() {
        this.uid = this.$route.query.id
    },
    mounted() {
        this.getMemberdata()
    },
  }
</script>