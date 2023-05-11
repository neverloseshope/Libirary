import Vue from "vue";
import VueRouter from "vue-router";

Vue.use(VueRouter);


export default new VueRouter({
  mode:'history',
  routes: [
    {
      path: "/",
      redirect: "/login",
    },
    {
      path: "/login",
      component: () => import("../views/login.vue"),
    },
    {
      path: "/home",
      name: "home",
      props: true,
      component: () => import("../views/home.vue"),
      children: [
        {
          path: "/book",
          component: () => import("../views/Book.vue"),
        },
        {
          path: "/publisher",
          component: () => import("../views/Publisher.vue"),
        },
        {
          path: "/borrower",
          component: () => import("../views/Borrower.vue"),
        },
        {
          path: "/borrowBook",
          component: () => import("../views/BorrowBook.vue"),
        },
        {
          path: "/returnBook",
          component: () => import("../views/RetuenBook.vue"),
        }
      ]
    },
    {
      path: "/test",
      component: () => import("../views/test.vue"),
    },
  ],
});



