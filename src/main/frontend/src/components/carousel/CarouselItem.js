import React from "react";
import styles from "../../css/carousel.module.css";

function CarouselItem({ item, width }) {
  return (
    <div className={styles.carousel__item} style={{ width: width }}>
      <img className={styles.carousel__img} src={item.image.default} />
      <div className={styles.carousel__img__text}>
        <p>{item.name}</p>
        <br />
        <p>{item.desc}</p>
        <div className={styles.carousel__link}>
          <button>{item.details}</button>
        </div>
      </div>
    </div>
  );
}

export default CarouselItem;
