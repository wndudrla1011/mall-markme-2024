import { Suspense, lazy } from "react";
import { createBrowserRouter } from "react-router-dom";

const Loading = <div className="bg-red-700">Loading...</div>;
const Main = lazy(() => import("../pages/MainPage"));

const root = createBrowserRouter([
  {
    path: "",
    element: (
      <Suspense fallback={Loading}>
        <Main />
      </Suspense>
    ),
  },
]);

export default root;
