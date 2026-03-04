export interface UserUpdateRequestDTO {
  id: string; // TODO: remove when auth is in place
  pseudo: string;
  email: string; // TODO: separate endpoint /users/change-email with confirmation flow
  // password excluded → dedicated endpoint /users/change-password with its own flow
}
