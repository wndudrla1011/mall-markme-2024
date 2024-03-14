import Carousel from "../components/carousel/Carousel.js";
import BasicMenu from "../components/menus/BasicMenu";
import carousel from "../css/carousel.module.css";

function MainLayout({ children }) {
  return (
    <>
      {/* 메뉴 */}
      <BasicMenu />

      {/* Carousel */}
      <div className={carousel.index}>
        <Carousel />
      </div>

      {/* Item List */}
      <div className="bg-green-700">{children}Iten List</div>
    </>
  );
}

export default MainLayout;
