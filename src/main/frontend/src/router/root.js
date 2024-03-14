import { Suspense, lazy } from "react";
import { createBrowserRouter } from "react-router-dom";
import Loading from "../pages/Loading";
import cartRouter from "./cartRouter";
import orderRouter from "./orderRouter";

const Main = lazy(() => {
  return Promise.all([
    import("../pages/MainPage"),
    new Promise((resolve) => setTimeout(resolve, 1000)),
  ]).then(([moduleExports]) => moduleExports);
});

const CartIndex = lazy(() => import("../pages/cart/IndexPage"));
const OrderIndex = lazy(() => import("../pages/order/IndexPage"));

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
]);

export default root;
