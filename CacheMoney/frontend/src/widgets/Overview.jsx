import React from "react";
import Options from "./Options";

const GeneralOptions = (props) => {
  const options = [
    {
      text: "Contact",
      handler: props.actionProvider.handleContactInfo,
      id: 1,
    },
    {
      text: "Atm locations",
      handler: props.actionProvider.handleAtmFinder,
      id: 2,
    },
    {
      text: "Recently asked questions",
      handler: props.actionProvider.handleQuestions,
      id: 3,
    },

    {
      text: "Information",
      handler: props.actionProvider.handleInformation,
      id: 4,
    },
  ];

  return <Options options={options} title="Options" {...props} />;
};

export default GeneralOptions;
