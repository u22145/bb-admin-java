import Vue from 'vue'
import Router from 'vue-router'
import home from '@/components/home'
import anchorContribution from '@/components/anchorContribution'
import BasicLayout from '@/components/BasicLayout'
import createGuild from '@/components/createGuild'
import dataDetail from '@/components/dataDetail'
import dateIncome from '@/components/dateIncome'
import editGuild from '@/components/editGuild'
import guildList from '@/components/guildList'
import guildReport from '@/components/guildReport'
import guildContribution  from '@/components/guildContribution'
import oneOnoneDetail from '@/components/oneOnoneDetail'
import oneOnoneHome from '@/components/oneOnoneHome'
import privateIncome from '@/components/privateIncome'
import rakeReport from '@/components/rakeReport'
import streamDetail from '@/components/streamDetail'
import streamHome from '@/components/streamHome'
import ugcDetail from '@/components/ugcDetail'
import guildOwnerManage from '@/components/guildOwnerManage'
import guildOwnerMemberManage from '@/components/guildOwnerMemberManage'
import guildOwnerInvite from '@/components/guildOwnerInvite'
import guildOwnerEdit from '@/components/guildOwnerEdit'
import performance from '@/components/performance'
import guildOwnerUGCReport from '@/components/guildOwnerUGCReport'
import guildOwnerDateReport from '@/components/guildOwnerDateReport'
import guildOwnerPrivate from '@/components/guildOwnerPrivate'
import guildOwnerIncome from '@/components/guildOwnerIncome'
import guildOwnerStreamReport from '@/components/guildOwnerStreamReport'
import guildOwnerStreamInvite from '@/components/guildOwnerStreamInvite'
import guildOwnerOneOnOneReport from '@/components/guildOwnerOneOnOneReport'
import guildOwnerOneOnOneInvite from '@/components/guildOwnerOneOnOneInvite'
import login from '@/components/login'
import loginAdmin from '@/components/loginAdmin'

Vue.use(Router)

var router = []

export default new Router({
  routes: [
    {
      path: '/',
      name: 'home',
      component: guildList
    },
    {
      path: '/anchorContribution',
      name: 'anchorContribution',
      component: anchorContribution
    },
    {
      path: '/BasicLayout',
      name: 'BasicLayout',
      component: BasicLayout
    },
    {
      path: '/createGuild',
      name: 'createGuild',
      component: createGuild
    },
    {
      path: '/dataDetail',
      name: 'dataDetail',
      component: dataDetail
    },
    {
      path: '/dateIncome',
      name: 'dateIncome',
      component: dateIncome
    },
    {
      path: '/editGuild',
      name: 'editGuild',
      component: editGuild
    },
    {
      path: '/guildContribution',
      name: 'guildContribution',
      component: guildContribution
    },
    {
      path: '/guildList',
      name: 'guildList',
      component: guildList
    },
    {
      path: '/guildReport',
      name: 'guildReport',
      component: guildReport
    },
    {
      path: '/oneOnoneDetail',
      name: 'oneOnoneDetail',
      component: oneOnoneDetail
    },
    {
      path: '/oneOnoneHome',
      name: 'oneOnoneHome',
      component: oneOnoneHome
    },
    {
      path: '/privateIncome',
      name: 'privateIncome',
      component: privateIncome
    },
    {
      path: '/rakeReport',
      name: 'rakeReport',
      component: rakeReport
    },
    {
      path: '/streamDetail',
      name: 'streamDetail',
      component: streamDetail
    },
    {
      path: '/streamHome',
      name: 'streamHome',
      component: streamHome
    },
    {
      path: '/ugcDetail',
      name: 'ugcDetail',
      component: ugcDetail
    },
    {
      path: '/guildOwnerManage',
      name: 'guildOwnerManage',
      component: guildOwnerManage
    },
    {
      path: '/guildOwnerMemberManage',
      name: 'guildOwnerMemberManage',
      component: guildOwnerMemberManage
    },
    {
      path: '/guildOwnerInvite',
      name: 'guildOwnerInvite',
      component: guildOwnerInvite
    },
    {
      path: '/guildOwnerEdit',
      name: 'guildOwnerEdit',
      component: guildOwnerEdit
    },
    {
      path: '/performance',
      name: 'performance',
      component: performance
    },
    {
      path: '/guildOwnerUGCReport',
      name: 'guildOwnerUGCReport',
      component: guildOwnerUGCReport
    },
    {
      path: '/guildOwnerDateReport',
      name: 'guildOwnerDateReport',
      component: guildOwnerDateReport
    },
    {
      path: '/guildOwnerPrivate',
      name: 'guildOwnerPrivate',
      component: guildOwnerPrivate
    },
    {
      path: '/guildOwnerIncome',
      name: 'guildOwnerIncome',
      component: guildOwnerIncome
    },
    {
      path: '/guildOwnerStreamReport',
      name: 'guildOwnerStreamReport',
      component: guildOwnerStreamReport
    },
    {
      path: '/guildOwnerStreamInvite',
      name: 'guildOwnerStreamInvite',
      component: guildOwnerStreamInvite
    },
    {
      path: '/guildOwnerOneOnOneReport',
      name: 'guildOwnerOneOnOneReport',
      component: guildOwnerOneOnOneReport
    },
    {
      path: '/guildOwnerOneOnOneInvite',
      name: 'guildOwnerOneOnOneInvite',
      component: guildOwnerOneOnOneInvite
    },
    {
      path: '/login',
      name: 'login',
      component: login
    },
    {
      path: '/loginAdmin',
      name: 'loginAdmin',
      component: loginAdmin
    }

  ]
})
