import React from "react";
import BasicLayout from "../../layouts/BasicLayout";
import { Outlet } from "react-router-dom";

function IndexPage() {
  return (
    <BasicLayout>
      <div>
        <Outlet />
      </div>
    </BasicLayout>
  );
}

export default IndexPage;
