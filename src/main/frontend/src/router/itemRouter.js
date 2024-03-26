import { Suspense, lazy } from "react";
import Loading from "./../pages/Loading";
import { Navigate } from "react-router-dom";

const ItemList = lazy(() => import("../pages/items/ListPage"));

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
  ];
};

export default itemRouter;
