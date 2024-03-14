import { Suspense, lazy } from "react";
import { createBrowserRouter } from "react-router-dom";
import Loading from "../pages/Loading";

// const Main = lazy(() => {
//   return Promise.all([
//     import("../pages/MainPage"),
//     new Promise((resolve) => setTimeout(resolve, 1200)),
//   ]).then(([moduleExports]) => moduleExports);
// });

const Main = lazy(() => import("../pages/MainPage"));

const root = createBrowserRouter([
  {
    path: "",
    element: (
      <Suspense fallback={<Loading />}>
        <Main />
      </Suspense>
    ),
  },
]);

export default root;
