import { Suspense, lazy } from "react";
import { createBrowserRouter } from "react-router-dom";
import Loading from "../pages/Loading";
import cartRouter from "./cartRouter";
import memberRouter from "./memberRouter";
import postsRouter from "./postsRouter";
import itemRouter from "./itemRouter";

const Main = lazy(() => {
  return Promise.all([
    import("../pages/MainPage"),
    new Promise((resolve) => setTimeout(resolve, 1000)),
  ]).then(([moduleExports]) => moduleExports);
});

const CartIndex = lazy(() => import("../pages/cart/IndexPage"));
const MemberIndex = lazy(() => import("../pages/member/IndexPage"));
const PostsIndex = lazy(() => import("../pages/posts/IndexPage"));
const ItemIndex = lazy(() => import("../pages/items/IndexPage"));

const root = createBrowserRouter([
  {
    path: "",
    element: (
      <Suspense fallback={<Loading />}>
        <Main />
      </Suspense>
    ),
  },
  {
    path: "cart",
    element: (
      <Suspense fallback={<Loading />}>
        <CartIndex />
      </Suspense>
    ),
    children: cartRouter(),
  },
  {
    path: "member",
    element: (
      <Suspense fallback={<Loading />}>
        <MemberIndex />
      </Suspense>
    ),
    children: memberRouter(),
  },
  {
    path: "posts",
    element: (
      <Suspense fallback={<Loading />}>
        <PostsIndex />
      </Suspense>
    ),
    children: postsRouter(),
  },
  {
    path: "items",
    element: (
      <Suspense fallback={<Loading />}>
        <ItemIndex />
      </Suspense>
    ),
    children: itemRouter(),
  },
]);

export default root;
