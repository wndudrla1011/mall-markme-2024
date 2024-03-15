import React, { Suspense, lazy } from "react";
import Loading from "../pages/Loading";
import { Navigate } from "react-router-dom";

const PostsList = lazy(() => import("../pages/posts/ListPage"));

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
  ];
};

export default postsRouter;
