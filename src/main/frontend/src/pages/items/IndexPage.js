import React from "react";
import BasicLayout from "../../layouts/BasicLayout";
import { Outlet } from "react-router-dom";

function IndexPage() {
  return (
    <BasicLayout>
      <div className="item__title text-lime-400 text-8xl py-10 text-center">
        Today's Mark Me
      </div>
      <div className="flex flex-wrap w-full">
        <Outlet />
      </div>
    </BasicLayout>
  );
}

export default IndexPage;
