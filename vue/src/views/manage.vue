<template>
  <el-container style="height: 100vh">
    <el-aside :width="sideWidth + 'px'" style="background-color: rgb(238, 241, 246); box-shadow: 3px 0 10px rgb(0 21 41 / 25%)">
    <vueAside :is-collapse="isCollapse" :logoTextShow="logoTextShow"></vueAside>
    </el-aside>
    <el-container>
      <!-- header -->
      <el-header style="border-bottom: 1px solid #e3e3e3;">
        <vueHeader :collapseBtnClass="collapseBtnClass" :collapse="collapse"></vueHeader>
      </el-header>
      <!-- main -->
      <el-main>
        <!--表示当前页面的子路由会在 router-view 中显示 -->
        <router-view></router-view>
      </el-main>

    </el-container>
  </el-container>
</template>
<script>
// 引入组件
import vueAside from '../components/vueAside.vue';
import vueHeader from '../components/vueHeader.vue';

export default {
  // eslint-disable-next-line vue/multi-word-component-names
  name: 'manage',
  data() {
    return {
      // 菜单
      shrinkageIcon: 'el-icon-s-fold',
      collapseBtnClass: 'el-icon-s-fold',
      isCollapse: false,
      sideWidth: 200,
      logoTextShow: true,
    };
  },
  // 注册组件
  components:{
    vueAside,
    vueHeader
  },
  methods: {
    collapse() {
      // 点击收缩按钮触发
      this.isCollapse = !this.isCollapse;
      if (this.isCollapse) {
        // 收缩
        this.sideWidth = 64;
        this.collapseBtnClass = 'el-icon-s-unfold';
        this.logoTextShow = false;
      } else {
        // 展开
        this.sideWidth = 200;
        this.collapseBtnClass = 'el-icon-s-fold';
        this.logoTextShow = true;
      }
    },

  }
};
</script>

<style>
.el-menu i {
  color: #e3e3e3;
}
.el-menu-item i {
  color: #e3e3e3;
}
.el-menu-item-group__title {
  display: none;
}
.el-menu-item-group .el-menu-item {
  background-color: #1f2d3d !important;
}
.el-menu-item {
  min-width: none;
}
.el-menu-item:hover {
  background-color: #001528 !important;
}
.el-menu {
  border-right: none;
}
.shrinkage {
  display: flex;
  float: left;
  height: 59px;
  width: 60px;
  margin-left: -20px;
}
.shrinkage:hover {
  background-color: #e8f4ff;
  cursor: pointer;
  transition: 0.4s;
}
.el-menu-vertical {
  transition: 0s -2s;
}
.el-aside {
  transition: 0s -2s;
}
.el-menu-vertical img {
  width: 20px;
  vertical-align: middle;
}
</style>
