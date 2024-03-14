import React, { useEffect, useState } from "react";
import styles from "../../css/carousel.module.css";
import CarouselItem from "./CarouselItem";

function Carousel() {
  const [activeIndex, setActiveIndex] = useState(0);

  const items = [
    {
      name: "Name",
      desc: "Description",
      details: "Details",
      image: require("../../media/search.svg"),
    },
    {
      name: "Name",
      desc: "Description",
      details: "Details",
      image: require("../../media/shopping.svg"),
    },
    {
      name: "Name",
      desc: "Description",
      details: "Details",
      image: require("../../media/calling.svg"),
    },
  ];

  const updateIndex = (newIndex) => {
    if (newIndex < 0) {
      newIndex = 0;
    } else if (newIndex >= items.length) {
      newIndex = items.length - 1;
    }

    setActiveIndex(newIndex);
  };

  return (
    <div className={styles.carousel}>
      <div
        className={styles.inner}
        style={{ transform: `translate(-${activeIndex * 100}%)` }}
      >
        {items.map((item) => {
          return <CarouselItem item={item} width={"100%"} />;
        })}
      </div>

      <div className={styles.carousel__buttons}>
        <button
          className={styles.button__arrow}
          onClick={() => {
            updateIndex(activeIndex - 1);
          }}
        >
          <span class="material-symbols-outlined">arrow_back_ios</span>
        </button>
        <div className={styles.indicators}>
          {items.map((item, index) => {
            return (
              <button
                className={styles.indicator__buttons}
                onClick={() => updateIndex(index)}
              >
                <span
                  className={`material-symbols-outlined ${
                    index === activeIndex
                      ? styles.indicator__symbol__active
                      : styles.indicator__symbol
                  }`}
                >
                  radio_button_checked
                </span>
              </button>
            );
          })}
        </div>
        <button
          className={styles.button__arrow}
          onClick={() => {
            updateIndex(activeIndex + 1);
          }}
        >
          <span class="material-symbols-outlined">arrow_forward_ios</span>
        </button>
      </div>
    </div>
  );
}

export default Carousel;
