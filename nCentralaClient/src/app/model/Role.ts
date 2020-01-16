export class Role {
    id : number;
    name : RoleName;
}

enum RoleName{
    ROLE_USER,
	ROLE_REVIEWER,
	ROLE_AUTOR,
	ROLE_EDITOR,
	ROLE_ADMIN
}