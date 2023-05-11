<template>
  <div id="home">
    <!-- 1.布局组件 -->
    <el-container>
      <el-header>
        <span>{{ $t('home.title') }}</span>
        <i class="el-icon-setting"></i>
        <el-select v-model="selectValue" @change="langChange" placeholder="selectValue" style="width: 100px;">
          <el-option v-for="item in options" :key="item.value" :label="item.label" :value="item.value">
          </el-option>
        </el-select>
      </el-header>
      <el-container>
        <el-aside width="200px">
          <!-- 2.侧边栏--菜单组件 -->
          <el-menu default-active="2" class="el-menu-vertical-demo" background-color="#999" text-color="#fff"
            active-text-color="#ffd04b" :router="true">
            <el-menu-item :index="menu.path" v-for="menu in menuList" :key="menu.id">
              {{ $t(menu.title) }}
            </el-menu-item>
          </el-menu>
        </el-aside>
        <!-- 3.右边栏--主边栏组件 -->
        <el-main>
          <router-view />
        </el-main>
      </el-container>
    </el-container>
  </div>
</template>
<script>
export default {
  data() {
    return {
      input: "",
      menuList: [
        {
          id: 1,
          title: "home.borrow",
          path: "/borrowBook",
        },
        {
          id: 2,
          title: "home.return",
          path: "/returnBook",
        },
        {
          id: 3,
          title: "home.book",
          path: "/book",
        },
        {
          id: 4,
          title: "home.borrower",
          path: "/borrower",
        },
        {
          id: 5,
          title: "home.publisher",
          path: "/publisher",
        },
      ],
      selectValue: '',
      options: [
        {
          value: 'zhcn',
          label: '中文'
        }, {
          value: 'en',
          label: 'English'
        }
      ],
      admin: {}
    };
  },
  created() {
    let that = this;
    console.log(localStorage.lang)
    that.selectValue = localStorage.lang == undefined ? 'cn' : localStorage.lang
  },
  mounted() {
    console.log(this.$route.params.admin);
    this.admin = this.$route.params.admin;
  },
  methods: {
    handleOpen(key, keyPath) {
      console.log(key, keyPath);
    },
    handleClose(key, keyPath) {
      console.log(key, keyPath);
    },//语言切换
    langChange(e) {
      localStorage.setItem('lang', e);
      this.$i18n.locale = e;
    }
  },

};
</script>
<style scoped>
.el-header {
  background-color: #859dbd;
  color: #333;
  text-align: center;
  line-height: 60px;
}
.el-header span{
  font-size: 35px;
  font-weight: bold;
  padding-right: 25%;
  padding-left: 35%;
}
.el-aside {
  background-color: #999;
  color: #333;
  text-align: center;
  line-height: 200px;
  height: 100ch;
}

.el-main {
  background-color: #e9eef3;
  color: #333;
  text-align: center;
  /* line-height: 30px; */
  height: 100ch;
}
</style>
