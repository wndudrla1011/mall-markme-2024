import React, { Suspense, lazy } from "react";
import Loading from "../pages/Loading";
import { Navigate } from "react-router-dom";

const MemberList = lazy(() => import("../pages/member/ListPage"));
const Login = lazy(() => import("../pages/member/LoginPage"));
const MemberAdd = lazy(() => import("../pages/member/AddPage"));
const MemberRead = lazy(() => import("../pages/member/ReadPage"));
const MemberEdit = lazy(() => import("../pages/member/EditPage"));

const memberRouter = () => {
  return [
    {
      path: "list",
      element: (
        <Suspense fallback={<Loading />}>
          <MemberList />
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
          <MemberAdd />
        </Suspense>
      ),
    },
    {
      path: "login",
      element: (
        <Suspense fallback={<Loading />}>
          <Login />
        </Suspense>
      ),
    },
    {
      path: "read/:memberId",
      element: (
        <Suspense fallback={<Loading />}>
          <MemberRead />
        </Suspense>
      ),
    },
    {
      path: "edit/:memberId",
      element: (
        <Suspense fallback={<Loading />}>
          <MemberEdit />
        </Suspense>
      ),
    },
  ];
};

export default memberRouter;
