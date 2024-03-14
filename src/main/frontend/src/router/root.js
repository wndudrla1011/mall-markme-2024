import { Suspense, lazy } from "react";
import { createBrowserRouter } from "react-router-dom";
import Loading from "../pages/Loading";
import cartRouter from "./cartRouter";
import orderRouter from "./orderRouter";
import memberRouter from "./memberRouter";

const Main = lazy(() => {
  return Promise.all([
    import("../pages/MainPage"),
    new Promise((resolve) => setTimeout(resolve, 1000)),
  ]).then(([moduleExports]) => moduleExports);
});

const CartIndex = lazy(() => import("../pages/cart/IndexPage"));
const OrderIndex = lazy(() => import("../pages/order/IndexPage"));
const MemberIndex = lazy(() => import("../pages/member/IndexPage"));

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
    path: "order",
    element: (
      <Suspense fallback={<Loading />}>
        <OrderIndex />
      </Suspense>
    ),
    children: orderRouter(),
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
]);

export default root;
