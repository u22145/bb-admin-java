<template>
    <div class="multipleInput">
        <div class="Breadcrumbs"><span>公会管理系统</span><span>公会管理</span><span>成员列表</span><span>檢視</span><span>添加 API未完成</span></div>
        <!-- input start -->
        <div class="information whiteBg">
            <el-form :label-position="labelPosition" label-width="28%"  >
                <el-form-item label="主播ID：">
                    <el-input v-model="uid" placeholder="请输入"></el-input>
                </el-form-item>
                <el-form-item label="绩效指标：">
                    <el-input v-model="performance" placeholder="请输入">
                        <template slot="append">收益 / 月</template>
                    </el-input>
                </el-form-item>
                <el-form-item label="达标次数：">
                    <el-input placeholder="请输入" disabled="true" v-model="anchorTiers[0].goalCount"></el-input>
                </el-form-item>
                <el-form-item label="礼物抽成：">
                    <el-input placeholder="请输入" v-model="anchorTiers[0].giftCommission">
                        <template slot="append">%</template>
                    </el-input>
                </el-form-item>
                <el-form-item label="彩票盈利抽成：">
                    <el-input placeholder="请输入" v-model="anchorTiers[0].lotteryCommission">
                        <template slot="append">%</template>
                    </el-input>
                </el-form-item>
                <el-form-item label="1V1视频抽成：">
                    <el-input placeholder="请输入" v-model="anchorTiers[0].privateStreamCommission">
                        <template slot="append">%</template>
                    </el-input>
                </el-form-item>
                <el-form-item label="约会电台抽成：">
                    <el-input placeholder="请输入" v-model="anchorTiers[0].datingCommission">
                        <template slot="append">%</template>
                    </el-input>
                </el-form-item>
                <el-form-item label="直播间门票抽成：">
                    <el-input placeholder="请输入" v-model="anchorTiers[0].streamTicketCommission">
                        <template slot="append">%</template>
                    </el-input>
                </el-form-item>
                <el-form-item label="UGC抽成：">
                    <el-input placeholder="请输入" v-model="anchorTiers[0].ugcCommission">
                        <template slot="append">%</template>
                    </el-input>
                </el-form-item>
                <el-form-item label="私信抽成：">
                    <el-input placeholder="请输入" v-model="anchorTiers[0].messageCommission">
                        <template slot="append">%</template>
                    </el-input>
                </el-form-item>
                <el-form-item label="状态：" class="statusOption">
                    <el-select v-model="value" placeholder="请选择">
                        <el-option v-for="item in options" :key="item.value" :label="item.label" :value="item.value"></el-option>
                    </el-select>
                </el-form-item>

        <!-- buttom start -->
        <el-row>
            <el-button @click="submit" type="primary">提交</el-button>
            <el-button>取消</el-button>
        </el-row>
        <!-- buttom end -->
            </el-form>
        </div>
        <!-- input end -->
    </div> 
    
</template>
<script>
  export default {
    data() {
      return {
        labelPosition: 'right',
        options: [{
            value: '1',
            label: '正常'
            }, {
            value: '2',
            label: '禁止'
        }],
        uid: "",
        performance: "",
        anchorTiers: [{
            goalCount: 1,
            giftCommission: "",
            lotteryCommission: "",
            privateStreamCommission: "",
            streamTicketCommission: "",
            ugcCommission: "",
            datingCommission: "",
            messageCommission: ""
        }]
      };
    },
    methods: {
        getMemberValue() {

        },
        submit() {
            this.$axios.post('gm/anchor', {
                uid: this.uid,
                performance: this.performance,
                anchorTiers: [{
                    goalCount: this.anchorTiers[0].goalCount,
                    giftCommission: this.anchorTiers[0].giftCommission/100,
                    lotteryCommission: this.anchorTiers[0].lotteryCommission/100,
                    privateStreamCommission: this.anchorTiers[0].privateStreamCommission/100,
                    streamTicketCommission: this.anchorTiers[0].streamTicketCommission/100,
                    ugcCommission: this.anchorTiers[0].ugcCommission/100,
                    datingCommission: this.anchorTiers[0].datingCommission/100,
                    messageCommission: this.anchorTiers[0].messageCommission/100
                }]
            })
            .then(function (response) {
                if(response.data.status == 1) {
                    // show invite success popup 
                }
            })
            .catch(function (error) {
                console.log(error);
            });
        }
    },
    mounted() {

    }
  }
</script>