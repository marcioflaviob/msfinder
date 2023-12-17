import React, { useState } from 'react'

import "./LanguageSelector.css"

export const LanguageSelector = ({ selected, setSelected }) => {

  const onClick = async () => {
    { selected ? setSelected(false) : setSelected(true) }
  }

  return (
    <div onClick={onClick} className="language-selector">
      <img className={selected ? "img-unselected" : ""} src='united-kingdom.png'></img>
      <img className={selected ? "" : "img-unselected"} src='brazil.png'></img>
    </div>
  )
}
