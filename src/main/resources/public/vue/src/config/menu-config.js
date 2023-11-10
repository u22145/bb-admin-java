module.exports = [{
    name: '公会管理',
    id: 'guild',
    sub: [{
        name: '公会列表',
        id: 1,
        sub:[
            {name: '檢視', componentName: 'guildList', id:1},
            {name: '新建', componentName: 'createGuild', id:2},
            {name: '删除', componentName: 'deleteGuild', id:3}
        ]
    },{
        name: '公会数据报表',
        id: 2,
        sub: [
            {name: '檢視', componentName: 'guildReport', id: 1},
            {
                name: '总数据详情',
                id: 2,
                sub:[
                    {name: '檢視', componentName: 'dataDetail', id:1},
                    {name: '直播间详情',  id:2, sub:[
                        {name: '檢視', componentName: 'streamHome', id: 1},
                        {name: '详情', componentName: 'streamDetail', id: 2}
                    ]},
                    {name: '1V1视频详情', id:3, sub:[
                        {name: '檢視', componentName: 'oneOnoneHome', id: 1},
                        {name: '详情', componentName: 'oneOnoneDetail', id: 2}                     
                    ]},
                    {name: 'UGC收益详情', componentName: 'ugcDetail', id:4},
                    {name: '约会电台收益详情', componentName: 'dateIncome', id:5},
                    {name: '私信收益详情', componentName: 'privateIncome', id:6}
                ]
            },
            {
                name: '公会贡献',
                id: 3,
                sub: [
                    {name: '檢視', componentName: 'guildContribution', id:1},
                    {name: '主播贡献', componentName: 'anchorContribution', id:2}
                ] 
            }
        ]
    },
    {name: '抽成结算报表', componentName: 'rakeReport', id:3}
    ]
},{
    name: '公会管理系统',
    id: 'guild-system',
    sub: [{
        name: '公会管理',
        id: 1,
        sub:[
            {name: '成员列表', id:1, sub:[
                {name: '檢視', componentName: 'guildOwnerMemberManage', id:1},
                {name: '邀请', componentName: 'guildOwnerInvite', id:2},
                {name: '编辑', componentName: 'guildOwnerEdit', id:3},
            ]},
            {name: '绩效考核', componentName: 'performance', id:2}
        ]
    },{
        name: '报表管理',
        id: 2,
        sub: [
            {name: '直播间报表',  id:1, sub:[
                {name: '檢視', componentName: 'guildOwnerStreamReport', id:1},
                {name: '详情', componentName: 'guildOwnerStreamInvite', id:2},
            ]},
            {name: '1V1视频报表',  id:2, sub:[
                {name: '檢視', componentName: 'guildOwnerOneOnOneReport', id:1},
                {name: '详情', componentName: 'guildOwnerOneOnOneInvite', id:2},
            ]},
            {name: 'UGC报表', componentName: 'guildOwnerUGCReport', id:3},
            {name: '约会电台报表', componentName: 'guildOwnerDateReport', id:4},
            {name: '私信收益报表', componentName: 'guildOwnerPrivate', id:5},
            {name: '收益报表', componentName: 'guildOwnerIncome', id:6}
        ]
    }]
}]