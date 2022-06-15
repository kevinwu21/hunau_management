<template>
  <div style="text-align: right; font-size: 12px;line-height: 60px">

    <!-- 折叠菜单栏 按钮 -->
    <div class="shrinkage" @click="collapse" style="flex: 1; font-size: 20px;display: flex;margin-top: -21px;">
      <span :class="collapseBtnClass" style="cursor: pointer; font-size: 25px; margin: auto; color: #5e6d82"></span>
    </div>

    <!-- 面包屑（页签）-->
  <el-breadcrumb separator-class="el-icon-arrow-right" style="align-items: center;display: flex; font-size: 16px; margin-top: 21px;margin-left: 60px;">
      <el-breadcrumb-item :to="'/'">首页</el-breadcrumb-item>
      <el-breadcrumb-item>{{ currentPathName }}</el-breadcrumb-item>
  </el-breadcrumb>

    <!-- 用户 -->
    <el-dropdown size="large" style="margin-top: -39px;display: flex;float: right;">
      <!-- 用户图标 -->
      <div style="cursor: pointer">
        <el-avatar style="margin-right: 10px; vertical-align: middle" size="small" :src="user.avatarUrl"></el-avatar>
        <span style="margin-right: 5px; vertical-align: middle">{{ user.nickname }}</span>
        <i class="el-icon-caret-bottom" style="margin-right: 5px; vertical-align: middle"></i>
      </div>
      <!-- 用户下拉菜单栏 -->
      <el-dropdown-menu slot="dropdown">
        <div @click="personPage">
          <el-dropdown-item prefix-icon="el-icon-tickets">
            用户信息
          </el-dropdown-item>
        </div>
        <div @click="logout">
          <el-dropdown-item prefix-icon="el-icon-tickets">
            退出登录
          </el-dropdown-item>
        </div>
      </el-dropdown-menu>
    </el-dropdown>
  </div>
</template>

<script>
export default {
  name: 'vueHeader',
  props:{
    collapseBtnClass: String,
    collapse: Function,
  },
  computed: {
    currentPathName () {
      //需要监听的数据
      return this.$store.state.currentPathName;
    }
  },
  watch: {
    currentPathName (newVal) {
      console.log(newVal)
    }
  },
  data() {
    return {
      user: localStorage.getItem("user") ? JSON.parse(localStorage.getItem("user")) : {}
    };
  },
  methods: {
    logout(){
      this.$router.push("/login")
      localStorage.removeItem("user")
      this.$message.success("退出成功")
    },
    personPage(){
      this.$router.push("/personPage")
    }
  },
};
</script>

<style>
.el-breadcrumb{
  line-height: 0;
}
</style>