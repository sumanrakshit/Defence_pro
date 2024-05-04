export interface Group
{

id ?:number;
name ?:String

}

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


export interface Users {
    id ?: String;
    username ?: string;
    name ?:String;
    password ?: string;
    role ?: Roles;
    is_FirstLogin ?:Boolean;
    ipaddress ?:String;
    created_date ?:number;
    creator_id ?:String;
    groups ?:Group;
    firstLogin ?:Boolean;
    httpStatus ?:String;
    token ?:String;



}