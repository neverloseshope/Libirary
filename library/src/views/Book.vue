<template>
  <div id="book">
    <!-- 1.面包屑 -->
    <el-breadcrumb separator="/">
      <el-breadcrumb-item :to="{ path: '/home' }">首页</el-breadcrumb-item>
      <el-breadcrumb-item>书籍列表</el-breadcrumb-item>
    </el-breadcrumb>
    <el-table
      :data="bookList"
      stripe
      style="width: 100%"
      :header-cell-style="{
        height: '60px',
        color: '#859dbd',
        padding: '0 0 0 20px',
      }"
    >
      <el-table-column label="书籍编号" width="200">
        <template slot-scope="scope">
          <span style="margin-left: 10px">{{ scope.row.id }}</span>
        </template>
      </el-table-column>
      <el-table-column label="书籍名称" width="200">
        <template slot-scope="scope">
          <span style="margin-left: 10px">{{ scope.row.name }}</span>
        </template>
      </el-table-column>
      <el-table-column label="书籍总量" width="200">
        <template slot-scope="scope">
          <span style="margin-left: 10px">{{ scope.row.number }}</span>
        </template>
      </el-table-column>
      <el-table-column label="索书位置" width="200">
        <template slot-scope="scope">
          <span style="margin-left: 10px">{{ scope.row.place }}</span>
        </template>
      </el-table-column>
      <el-table-column label="出版社" width="200">
        <template slot-scope="scope">
          <span style="margin-left: 10px">{{ scope.row.publisher.name }}</span>
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
      :title="operate + '书籍'"
      :visible.sync="dialogVisible"
      width="30%"
    >
      <el-form ref="book" :model="book" label-width="80px">
        <el-form-item label="编号" hidden>
          <el-input v-model="book.id"></el-input>
        </el-form-item>
        <el-form-item label="名称">
          <el-input v-model="book.name"></el-input>
        </el-form-item>
        <el-form-item label="总量">
          <el-input v-model="book.number"></el-input>
        </el-form-item>
        <el-form-item label="位置">
          <el-input v-model="book.place"></el-input>
        </el-form-item>
        <el-form-item label="出版社">
          <el-select v-model="book.publisher.id">
            <el-option
              v-for="publisher in publisherList"
              :value="publisher.id"
              :label="publisher.name"
              :key="publisher.id"
            ></el-option>
          </el-select>
        </el-form-item>
        <el-form-item>
          <el-button type=" primary" @click="submit(book)">{{
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
      bookList: [],
      currentPage: 1, //当前页
      pageSize: 5, //每页多少条记录
      total: 0, //总记录数
      pages: 0, //总页数
      dialogVisible: false,
      book: {
        id: "",
        name: "",
        number: "",
        place: "",
        publisher: {
          id: "",
          name: "",
        },
      },
      publisherList: [],
      operate: "",
    };
  },
  created() {
    this.selectList();
    this.selectPublisherList();
  },
  methods: {
    async selectList() {
      const res = await this.$axios.get("/book/selectPageList", {
        params: {
          currentPage: this.currentPage,
          pageSize: this.pageSize,
        },
      });
      if (res.status != 200) {
        this.$message("获取数据失败");
        return;
      }
      this.bookList = res.data.records;
      this.total = res.data.total;
      this.pages = res.data.pages;
    },
    handleSizeChange(newPageSize) {
      this.pageSize = newPageSize;
      this.selectList();
    },
    handleCurrentChange(newCurrentPage) {
      this.currentPage = newCurrentPage;
      this.selectList();
    },
    submit(book) {
      if (this.operate == "新增") {
        this.addBook(book);
      } else {
        this.updateBook(book);
      }
    },
    handleAdd() {
      this.operate = "新增";
      this.clearBook()
      this.dialogVisible = true;
    },
    async addBook(book) {
      const res = await this.$axios.post("/book/addBook", book);
      if (res.status != 200) {
        this.$message("新增失败");
        return;
      }
      this.$message("新增成功")
      this.dialogVisible = false;
      this.clearBook()
      this.selectList();
    },
    handleEdit(id) {
      this.operate = "编辑";
      this.selectBook(id);
      this.dialogVisible = true;
    },
    async selectBook(id) {
      const res = await this.$axios.get("/book/queryById?id=" + id);
      if (res.status != 200) {
        this.$message("获取数据失败");
        return;
      }
      this.book = res.data;
    },
    async updateBook(book) {
      const res = await this.$axios.post("/book/updateBook", book);
      if (res.status != 200) {
        this.$message("更新失败");
        return;
      }
      this.$message("新增成功")
      this.dialogVisible = false;
      this.clearBook()
      this.selectList();
    },
    handleDelete(id) {
      this.deleteBook(id);
    },
    async deleteBook(id) {
      const res = await this.$axios.get("/book/deleteById?id=" + id);
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
    async clearBook(){
      this.book = {
        id: "",
        name: "",
        number: "",
        place: "",
        publisher: {
          id: "",
          name: "",
        },
      }
    },
    async selectPublisherList() {
      const res = await this.$axios.get("/publisher/allPublisher");
      this.publisherList = res.data;
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
