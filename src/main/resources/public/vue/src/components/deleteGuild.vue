<template>
    <div class="multipleInput">
        <div class="Breadcrumbs"><span>公会管理</span><span>公会列表</span><span>編輯公會</span></div>
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
                    <el-select v-model="value" placeholder="请选择">
                        <el-option v-for="item in options" :key="item.value" :label="item.label" :value="item.value"></el-option>
                    </el-select>
                </el-form-item>
            </el-form>

        <!-- input end -->
        <!-- buttom start -->
        <el-row>
            <el-button plain @click="editSubmit" type="primary">提交</el-button>
            <el-button @click="goBack">取消</el-button>
            <el-button type="danger" @click="editDelete" plain>删除</el-button>
            
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
            value: '1',
            label: '正常'
            }, {
            value: '2',
            label: '禁止'
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
        value: '',
        guildId: ''
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
                    this.giftCommission = data.giftCommission
                    this.lotteryCommission = data.lotteryCommission
                    this.privateStreamCommission = data.privateStreamCommission
                    this.streamTicketCommission = data.streamTicketCommission
                    this.ugcCommission = data.ugcCommission
                    this.datingCommission = data.datingCommission
                    this.messageCommission = data.messageCommission
                    this.status = data.status
            }.bind(this))
            .catch(function (error) {
                console.log('请求失败的返回值');
            });
        },
        editSubmit() {
            this.$axios.post('guild/', {
                id: this.guildId,
                giftCommission: this.giftCommission,
                lotteryCommission: this.lotteryCommission,
                privateStreamCommission: this.privateStreamCommission,
                streamTicketCommission: this.streamTicketCommission,
                ugcCommission: this.ugcCommission,
                datingCommission: this.datingCommission,
                messageCommission: this.messageCommission,
                status: this.value
            })
            .then(function (response) {
                console.log(response);
            })
            .catch(function (error) {
                console.log(error);
            });
            this.$notify({
            title: '成功',
            message: '编辑成功',
            type: 'success',
            duration: 2000
            });
        },
        goBack() {
            this.$router.go(-1)
        },
        editDelete(){
            this.$notify({
            title: '删除公会',
            type: 'warning',
            dangerouslyUseHTMLString: true,
            message: '<span style="color:red;">删除后该公会抽成将无法正常计算，操作不可逆</span>，你还要继续吗？<br><button >取消</button> <button >確定刪除</button>'
            });
        }
        
    }
  }
</script>