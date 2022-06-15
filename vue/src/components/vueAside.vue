<template>
      <el-menu
        class="el-menu-vertical"
        :default-openeds="['1', '2']"
        style="min-height: 100%; overflow-x: hidden"
        background-color="#304156"
        text-color="#e3e3e3"
        active-text-color="#43b883"
        :collapse="isCollapse"
        router
        @select="handleSelect"
      >
        <div style="height: 61px; line-height: 61px; text-align: center">
          <img src="../assets/logo.png" />
          <b style="color: white; vertical-align: middle; margin-left: 10px" v-show="logoTextShow">后台管理系统</b>
        </div>

        <el-menu-item index="/">
          <i class="el-icon-s-home"></i>
          <span>首页</span>
        </el-menu-item>

        <el-submenu index="2">
          <template slot="title">
            <i class="el-icon-menu"></i>
            <span>系统管理</span>
          </template>
          <el-menu-item-group>
            <el-menu-item index="/userInfo">
              <i class="el-icon-user"></i>
              <span>用户管理</span>
            </el-menu-item>
            <!-- <el-menu-item index="2-2">
              <i class="el-icon-user"></i>
              <span>选项2</span>
            </el-menu-item> -->
          </el-menu-item-group>
        </el-submenu>

        <!-- <el-submenu index="3">
          <template slot="title">
            <i class="el-icon-setting"></i>
            <span>导航三</span>
          </template>
          <el-menu-item-group>
            <el-menu-item index="3-1">
              <i class="el-icon-user"></i>
              <span>选项1</span>
            </el-menu-item>
            <el-menu-item index="3-2">
              <i class="el-icon-user"></i>
              <span>选项2</span>
            </el-menu-item>
          </el-menu-item-group>
        </el-submenu> -->
      </el-menu>
</template>

<script>
export default {
  name: 'vueAside',
  props:{
    isCollapse: Boolean,
    logoTextShow: Boolean
  },
  methods: {
    handleSelect(index){
      console.log(index);
    }
  },
};
</script>

<style>

</style>