import React, { useCallback } from "react";
import BasicLayout from "../../layouts/BasicLayout";
import { Outlet, useNavigate } from "react-router-dom";

function IndexPage() {
  const navigate = useNavigate();

  const handleClickAdd = useCallback(() => {
    navigate({ pathname: "add" });
  });

  return (
    <BasicLayout>
      <div className="item__title text-lime-400 text-8xl py-10 text-center">
        Today's Mark Me
      </div>
      <div className="flex flex-wrap w-full">
        <Outlet />
      </div>
      <div className="text-3xl text-white">REGISTER</div>
    </BasicLayout>
  );
}

export default IndexPage;
