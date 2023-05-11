<template>
  <div class="borrower">
    <!-- 1.面包屑 -->
    <el-breadcrumb separator="/">
      <el-breadcrumb-item :to="{ path: '/home' }">首页</el-breadcrumb-item>
      <el-breadcrumb-item>出版社列表</el-breadcrumb-item>
    </el-breadcrumb>
    <el-table
      :data="borrowerList"
      stripe
      style="width: 100%"
      :header-cell-style="{
        height: '60px',
        color: '#859dbd',
        padding: '0 0 0 20px',
      }"
    >
      <el-table-column label="借阅人编号" width="200">
        <template slot-scope="scope">
          <span style="margin-left: 10px">{{ scope.row.id }}</span>
        </template>
      </el-table-column>
      <el-table-column label="借阅人姓名" width="200">
        <template slot-scope="scope">
          <span style="margin-left: 10px">{{ scope.row.name }}</span>
        </template>
      </el-table-column>
      <el-table-column label="联系电话" width="200">
        <template slot-scope="scope">
          <span style="margin-left: 10px">{{ scope.row.phone }}</span>
        </template>
      </el-table-column>
      <el-table-column label="操作">
        <template slot-scope="scope">
          <el-button
            size="mini"
            @click="handleEdit(scope.row.id)"
            icon="el-icon-edit"
            >编辑</el-button
          >
          <el-button
            size="mini"
            type="danger"
            @click="handleDelete(scope.row.id)"
            icon="el-icon-delete"
            >删除</el-button
          >
        </template>
      </el-table-column>
    </el-table>
    <div class="tool">
      <el-button type="primary" @click="handleAdd()"
        ><i class="el-icon-upload el-icon--right"></i>新增</el-button
      >
      <el-pagination
        @size-change="handleSizeChange"
        @current-change="handleCurrentChange"
        :current-page="currentPage"
        :page-sizes="[3, 5, 10]"
        :page-size="5"
        layout="total, sizes, prev, pager, next, jumper"
        :total="total"
      >
      </el-pagination>
    </div>

    <!--新增/修改对话框 -->
    <el-dialog
      :title="operate + '出版社'"
      :visible.sync="dialogVisible"
      width="30%"
    >
      <el-form ref="borrower" :model="borrower" label-width="80px">
        <el-form-item label="编号" hidden>
          <el-input v-model="borrower.id"></el-input>
        </el-form-item>
        <el-form-item label="姓名">
          <el-input v-model="borrower.name"></el-input>
        </el-form-item>
        <el-form-item label="电话">
          <el-input v-model="borrower.phone"></el-input>
        </el-form-item>
        <el-form-item>
          <el-button type="primary" @click="submit(borrower)">{{
            operate
          }}</el-button>
          <el-button @click="dialogVisible = false">取 消</el-button>
        </el-form-item>
      </el-form>
    </el-dialog>
  </div>
</template>
<script>
export default {
  data() {
    return {
      borrowerList: [],
      currentPage: 1, //当前页
      pageSize: 5, //每页多少条记录
      total: 0, //总记录数
      pages: 0, //总页数
      dialogVisible: false,
      borrower: {
        id: "",
        name: "",
        phone: "",
      },
      operate: "",
    };
  },
  created() {
    this.selectList();
  },
  methods: {
    async selectList() {
      const res = await this.$axios.get("/borrower/selectPageList", {
        params: {
          currentPage: this.currentPage,
          pageSize: this.pageSize,
        },
      });
      if (res.status != 200) {
        this.$message("获取数据失败");
        return;
      }
      this.borrowerList = res.data.records;
      this.total = res.data.total;
      this.pages = res.data.pages
    },
    handleSizeChange(newPageSize) {
      this.pageSize = newPageSize;
      this.selectList();
    },
    handleCurrentChange(newCurrentPage) {
      this.currentPage = newCurrentPage;
      this.selectList();
    },
    submit(borrower) {
      if (this.operate == "新增") {
        this.addBorrower(borrower);
      } else {
        this.updateBorrower(borrower);
      }
    },
    handleAdd() {
      this.operate = "新增";
      this.borrower = {};
      this.dialogVisible = true;
    },
    async addBorrower(borrower) {
      const res = await this.$axios.post("/borrower/addBorrower", borrower);
      if (res.status != 200) {
        this.$message("新增失败");
        return;
      }
      this.dialogVisible = false;
      this.borrower = {};
      this.selectList();
    },
    handleEdit(id) {
      this.operate = "修改";
      this.selectBorrower(id);
      this.dialogVisible = true;
    },
    async selectBorrower(id) {
      const res = await this.$axios.get("/borrower/queryById?id=" + id);
      if (res.status != 200) {
        this.$message("获取数据失败");
        return;
      }
      this.borrower = res.data;
    },
    async updateBorrower(borrower) {
      const res = await this.$axios.post("/borrower/updateBorrower", borrower);
      if (res.status != 200) {
        this.$message("更新失败");
        return;
      }
      this.$message("更新成功")
      this.dialogVisible = false;
      this.borrower = {};
      this.selectList();
    },
    handleDelete(id) {
      this.deleteBorrower(id);
    },
    async deleteBorrower(id) {
      const res = await this.$axios.get("/borrower/deleteById?id=" + id);
      if (res.status != 200) {
        this.$message("删除失败");
        return;
      }
      this.$message("删除成功");
      if((this.total%this.pageSize)==1 && this.currentPage!=1 && this.currentPage==this.pages){
        this.currentPage--;
      }
      this.selectList();
    },
  },
};
</script>
<style scoped>
.el-table {
  margin-top: 10px;
}

.tool {
  position: relative;
  display: flex;
  width: 100%;
  height: 40px;
  margin-top: 10px;
}

.tool .el-pagination {
  position: absolute;
  left: 50%;
  transform: translateX(-50%);
}
</style>
