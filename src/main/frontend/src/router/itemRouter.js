import { Suspense, lazy } from "react";
import Loading from "./../pages/Loading";
import { Navigate } from "react-router-dom";

const ItemList = lazy(() => import("../pages/items/ListPage"));
const ItemAdd = lazy(() => import("../pages/items/AddPage"));

const itemRouter = () => {
  return [
    {
      path: "list",
      element: (
        <Suspense fallback={<Loading />}>
          <ItemList />
        </Suspense>
      ),
    },
    {
      path: "",
      element: <Navigate replace={true} to={"list"} />,
    },
    {
      path: "add",
      element: (
        <Suspense fallback={<Loading />}>
          <ItemAdd />
        </Suspense>
      ),
    },
  ];
};

export default itemRouter;
