
import { DevData } from "./../DevelopersData";

export default function ContactEntry(props) {

  // let info = DevData.map((dd) => {
  //   return dd;
  // });

  return (
    <>
      <div className="contact-entry-conatiner">
    
        Name: {props.name} GitHub: {props.github}
      </div>
    </>
  );
}
