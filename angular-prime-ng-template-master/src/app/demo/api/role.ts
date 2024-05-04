
export interface privilege{
    id ?: String;
    name ?:String;
}



export interface  Roles {
   id ?: String;
   name ? : String;
   org ? : String;
   privilege ? : privilege;
   created_date ?: number;



}