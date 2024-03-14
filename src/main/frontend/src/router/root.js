import { Suspense, lazy } from "react";
import { createBrowserRouter } from "react-router-dom";
import Loading from "../pages/Loading";
import cartRouter from "./cartRouter";

// const Main = lazy(() => {
//   return Promise.all([
//     import("../pages/MainPage"),
//     new Promise((resolve) => setTimeout(resolve, 1200)),
//   ]).then(([moduleExports]) => moduleExports);
// });

const Main = lazy(() => import("../pages/MainPage"));
const CartIndex = lazy(() => import("../pages/cart/IndexPage"));

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
]);

export default root;
