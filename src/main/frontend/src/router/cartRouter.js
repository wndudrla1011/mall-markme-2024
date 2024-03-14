import React, { Suspense, lazy } from "react";
import Loading from "../pages/Loading";

const CartList = lazy(() => import("../pages/cart/ListPage"));

const cartRouter = () => {
  return [
    {
      path: "list",
      element: (
        <Suspense fallback={<Loading />}>
          <CartList />
        </Suspense>
      ),
    },
  ];
};

export default cartRouter;
