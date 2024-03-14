import React, { Suspense, lazy } from "react";
import Loading from "../pages/Loading";
import { Navigate } from "react-router-dom";

const OrderList = lazy(() => import("../pages/order/ListPage"));
const OrderRead = lazy(() => import("../pages/order/ReadPage"));

const orderRouter = () => {
  return [
    {
      path: "list",
      element: (
        <Suspense fallback={<Loading />}>
          <OrderList />
        </Suspense>
      ),
    },
    {
      path: "",
      element: <Navigate replace={true} to={"list"} />,
    },
    {
      path: "read/:orderId",
      element: (
        <Suspense fallback={<Loading />}>
          <OrderRead />
        </Suspense>
      ),
    },
  ];
};

export default orderRouter;
