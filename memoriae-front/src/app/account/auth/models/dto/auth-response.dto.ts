export interface AuthResponseDto {
  accessToken: string;
  refreshToken: string;
  userId: string;
  pseudo: string;
  expiresIn: number;
}
