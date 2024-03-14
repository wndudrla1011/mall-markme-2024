import React from "react";
import Carousel from "../components/carousel/Carousel";
import carousel from "../css/carousel.module.css";
import BasicLayout from "./../layouts/BasicLayout";

function MainPage() {
  return (
    <BasicLayout>
      {/* Carousel */}
      <div className={carousel.index}>
        <Carousel />
      </div>

      {/* Item List */}
    </BasicLayout>
  );
}

export default MainPage;
