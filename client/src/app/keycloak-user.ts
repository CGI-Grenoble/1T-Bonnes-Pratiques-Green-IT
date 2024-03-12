export interface KeycloakCustomProfile {
	id?: string;
	username?: string;
	email?: string;
	firstName?: string;
	lastName?: string;
	enabled?: boolean;
	emailVerified?: boolean;
	totp?: boolean;
	createdTimestamp?: number;
  attributes?: Array<Object>;
}
