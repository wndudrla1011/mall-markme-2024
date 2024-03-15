import React, { Suspense, lazy } from "react";
import Loading from "../pages/Loading";
import { Navigate } from "react-router-dom";

const PostsList = lazy(() => import("../pages/posts/ListPage"));
const PostsRead = lazy(() => import("../pages/posts/ReadPage"));
const PostsEdit = lazy(() => import("../pages/posts/EditPage"));
const PostsAdd = lazy(() => import("../pages/posts/AddPage"));

const postsRouter = () => {
  return [
    {
      path: "list",
      element: (
        <Suspense fallback={<Loading />}>
          <PostsList />
        </Suspense>
      ),
    },
    {
      path: "",
      element: <Navigate replace={true} to={"list"} />,
    },
    {
      path: "read/:postId",
      element: (
        <Suspense fallback={<Loading />}>
          <PostsRead />
        </Suspense>
      ),
    },
    {
      path: "add",
      element: (
        <Suspense fallback={<Loading />}>
          <PostsAdd />
        </Suspense>
      ),
    },
    {
      path: "edit/:postId",
      element: (
        <Suspense fallback={<Loading />}>
          <PostsEdit />
        </Suspense>
      ),
    },
  ];
};

export default postsRouter;
