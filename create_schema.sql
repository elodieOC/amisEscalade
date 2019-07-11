--
-- PostgreSQL database dump
--

-- Dumped from database version 10.7
-- Dumped by pg_dump version 10.7

-- Started on 2019-07-11 19:41:35

SET statement_timeout = 0;
SET lock_timeout = 0;
SET idle_in_transaction_session_timeout = 0;
SET client_encoding = 'UTF8';
SET standard_conforming_strings = on;
SELECT pg_catalog.set_config('search_path', '', false);
SET check_function_bodies = false;
SET client_min_messages = warning;
SET row_security = off;

--
-- TOC entry 1 (class 3079 OID 12924)
-- Name: plpgsql; Type: EXTENSION; Schema: -; Owner: 
--

CREATE EXTENSION IF NOT EXISTS plpgsql WITH SCHEMA pg_catalog;


--
-- TOC entry 2926 (class 0 OID 0)
-- Dependencies: 1
-- Name: EXTENSION plpgsql; Type: COMMENT; Schema: -; Owner: 
--

COMMENT ON EXTENSION plpgsql IS 'PL/pgSQL procedural language';


SET default_tablespace = '';

SET default_with_oids = false;

--
-- TOC entry 197 (class 1259 OID 23225)
-- Name: climb_user; Type: TABLE; Schema: public; Owner: postgres
--

CREATE TABLE public.climb_user (
    id integer NOT NULL,
    email character varying(255),
    password character varying(255),
    username character varying(255),
    role_fk integer
);


ALTER TABLE public.climb_user OWNER TO postgres;

--
-- TOC entry 196 (class 1259 OID 23223)
-- Name: climb_user_id_seq; Type: SEQUENCE; Schema: public; Owner: postgres
--

CREATE SEQUENCE public.climb_user_id_seq
    AS integer
    START WITH 1
    INCREMENT BY 1
    NO MINVALUE
    NO MAXVALUE
    CACHE 1;


ALTER TABLE public.climb_user_id_seq OWNER TO postgres;

--
-- TOC entry 2927 (class 0 OID 0)
-- Dependencies: 196
-- Name: climb_user_id_seq; Type: SEQUENCE OWNED BY; Schema: public; Owner: postgres
--

ALTER SEQUENCE public.climb_user_id_seq OWNED BY public.climb_user.id;


--
-- TOC entry 199 (class 1259 OID 23236)
-- Name: comment; Type: TABLE; Schema: public; Owner: postgres
--

CREATE TABLE public.comment (
    id integer NOT NULL,
    content character varying(600),
    date character varying(255),
    spot_fk integer,
    climb_user_fk integer
);


ALTER TABLE public.comment OWNER TO postgres;

--
-- TOC entry 198 (class 1259 OID 23234)
-- Name: comment_id_seq; Type: SEQUENCE; Schema: public; Owner: postgres
--

CREATE SEQUENCE public.comment_id_seq
    AS integer
    START WITH 1
    INCREMENT BY 1
    NO MINVALUE
    NO MAXVALUE
    CACHE 1;


ALTER TABLE public.comment_id_seq OWNER TO postgres;

--
-- TOC entry 2928 (class 0 OID 0)
-- Dependencies: 198
-- Name: comment_id_seq; Type: SEQUENCE OWNED BY; Schema: public; Owner: postgres
--

ALTER SEQUENCE public.comment_id_seq OWNED BY public.comment.id;


--
-- TOC entry 216 (class 1259 OID 23389)
-- Name: flyway_schema_history; Type: TABLE; Schema: public; Owner: postgres
--

CREATE TABLE public.flyway_schema_history (
    installed_rank integer NOT NULL,
    version character varying(50),
    description character varying(200) NOT NULL,
    type character varying(20) NOT NULL,
    script character varying(1000) NOT NULL,
    checksum integer,
    installed_by character varying(100) NOT NULL,
    installed_on timestamp without time zone DEFAULT now() NOT NULL,
    execution_time integer NOT NULL,
    success boolean NOT NULL
);


ALTER TABLE public.flyway_schema_history OWNER TO postgres;

--
-- TOC entry 201 (class 1259 OID 23247)
-- Name: grade; Type: TABLE; Schema: public; Owner: postgres
--

CREATE TABLE public.grade (
    id integer NOT NULL,
    name character varying(255),
    rating_fk integer
);


ALTER TABLE public.grade OWNER TO postgres;

--
-- TOC entry 200 (class 1259 OID 23245)
-- Name: grade_id_seq; Type: SEQUENCE; Schema: public; Owner: postgres
--

CREATE SEQUENCE public.grade_id_seq
    AS integer
    START WITH 1
    INCREMENT BY 1
    NO MINVALUE
    NO MAXVALUE
    CACHE 1;


ALTER TABLE public.grade_id_seq OWNER TO postgres;

--
-- TOC entry 2929 (class 0 OID 0)
-- Dependencies: 200
-- Name: grade_id_seq; Type: SEQUENCE OWNED BY; Schema: public; Owner: postgres
--

ALTER SEQUENCE public.grade_id_seq OWNED BY public.grade.id;


--
-- TOC entry 203 (class 1259 OID 23255)
-- Name: length; Type: TABLE; Schema: public; Owner: postgres
--

CREATE TABLE public.length (
    id integer NOT NULL,
    bolts integer,
    height double precision,
    grade_fk integer,
    route_fk integer,
    climb_user_fk integer
);


ALTER TABLE public.length OWNER TO postgres;

--
-- TOC entry 202 (class 1259 OID 23253)
-- Name: length_id_seq; Type: SEQUENCE; Schema: public; Owner: postgres
--

CREATE SEQUENCE public.length_id_seq
    AS integer
    START WITH 1
    INCREMENT BY 1
    NO MINVALUE
    NO MAXVALUE
    CACHE 1;


ALTER TABLE public.length_id_seq OWNER TO postgres;

--
-- TOC entry 2930 (class 0 OID 0)
-- Dependencies: 202
-- Name: length_id_seq; Type: SEQUENCE OWNED BY; Schema: public; Owner: postgres
--

ALTER SEQUENCE public.length_id_seq OWNED BY public.length.id;


--
-- TOC entry 218 (class 1259 OID 23422)
-- Name: news_suscriber; Type: TABLE; Schema: public; Owner: postgres
--

CREATE TABLE public.news_suscriber (
    id integer NOT NULL,
    email character varying(255)
);


ALTER TABLE public.news_suscriber OWNER TO postgres;

--
-- TOC entry 217 (class 1259 OID 23420)
-- Name: news_suscriber_id_seq; Type: SEQUENCE; Schema: public; Owner: postgres
--

CREATE SEQUENCE public.news_suscriber_id_seq
    AS integer
    START WITH 1
    INCREMENT BY 1
    NO MINVALUE
    NO MAXVALUE
    CACHE 1;


ALTER TABLE public.news_suscriber_id_seq OWNER TO postgres;

--
-- TOC entry 2931 (class 0 OID 0)
-- Dependencies: 217
-- Name: news_suscriber_id_seq; Type: SEQUENCE OWNED BY; Schema: public; Owner: postgres
--

ALTER SEQUENCE public.news_suscriber_id_seq OWNED BY public.news_suscriber.id;


--
-- TOC entry 205 (class 1259 OID 23263)
-- Name: rating; Type: TABLE; Schema: public; Owner: postgres
--

CREATE TABLE public.rating (
    id integer NOT NULL,
    name character varying(255)
);


ALTER TABLE public.rating OWNER TO postgres;

--
-- TOC entry 204 (class 1259 OID 23261)
-- Name: rating_id_seq; Type: SEQUENCE; Schema: public; Owner: postgres
--

CREATE SEQUENCE public.rating_id_seq
    AS integer
    START WITH 1
    INCREMENT BY 1
    NO MINVALUE
    NO MAXVALUE
    CACHE 1;


ALTER TABLE public.rating_id_seq OWNER TO postgres;

--
-- TOC entry 2932 (class 0 OID 0)
-- Dependencies: 204
-- Name: rating_id_seq; Type: SEQUENCE OWNED BY; Schema: public; Owner: postgres
--

ALTER SEQUENCE public.rating_id_seq OWNED BY public.rating.id;


--
-- TOC entry 207 (class 1259 OID 23271)
-- Name: roles; Type: TABLE; Schema: public; Owner: postgres
--

CREATE TABLE public.roles (
    id integer NOT NULL,
    role_name character varying(255)
);


ALTER TABLE public.roles OWNER TO postgres;

--
-- TOC entry 206 (class 1259 OID 23269)
-- Name: roles_id_seq; Type: SEQUENCE; Schema: public; Owner: postgres
--

CREATE SEQUENCE public.roles_id_seq
    AS integer
    START WITH 1
    INCREMENT BY 1
    NO MINVALUE
    NO MAXVALUE
    CACHE 1;


ALTER TABLE public.roles_id_seq OWNER TO postgres;

--
-- TOC entry 2933 (class 0 OID 0)
-- Dependencies: 206
-- Name: roles_id_seq; Type: SEQUENCE OWNED BY; Schema: public; Owner: postgres
--

ALTER SEQUENCE public.roles_id_seq OWNED BY public.roles.id;


--
-- TOC entry 209 (class 1259 OID 23279)
-- Name: route; Type: TABLE; Schema: public; Owner: postgres
--

CREATE TABLE public.route (
    id integer NOT NULL,
    image oid,
    name character varying(255),
    sector_fk integer,
    climb_user_fk integer
);


ALTER TABLE public.route OWNER TO postgres;

--
-- TOC entry 208 (class 1259 OID 23277)
-- Name: route_id_seq; Type: SEQUENCE; Schema: public; Owner: postgres
--

CREATE SEQUENCE public.route_id_seq
    AS integer
    START WITH 1
    INCREMENT BY 1
    NO MINVALUE
    NO MAXVALUE
    CACHE 1;


ALTER TABLE public.route_id_seq OWNER TO postgres;

--
-- TOC entry 2934 (class 0 OID 0)
-- Dependencies: 208
-- Name: route_id_seq; Type: SEQUENCE OWNED BY; Schema: public; Owner: postgres
--

ALTER SEQUENCE public.route_id_seq OWNED BY public.route.id;


--
-- TOC entry 211 (class 1259 OID 23287)
-- Name: sector; Type: TABLE; Schema: public; Owner: postgres
--

CREATE TABLE public.sector (
    id integer NOT NULL,
    access character varying(500),
    image oid,
    location character varying(255),
    name character varying(255),
    spot_fk integer,
    climb_user_fk integer
);


ALTER TABLE public.sector OWNER TO postgres;

--
-- TOC entry 210 (class 1259 OID 23285)
-- Name: sector_id_seq; Type: SEQUENCE; Schema: public; Owner: postgres
--

CREATE SEQUENCE public.sector_id_seq
    AS integer
    START WITH 1
    INCREMENT BY 1
    NO MINVALUE
    NO MAXVALUE
    CACHE 1;


ALTER TABLE public.sector_id_seq OWNER TO postgres;

--
-- TOC entry 2935 (class 0 OID 0)
-- Dependencies: 210
-- Name: sector_id_seq; Type: SEQUENCE OWNED BY; Schema: public; Owner: postgres
--

ALTER SEQUENCE public.sector_id_seq OWNED BY public.sector.id;


--
-- TOC entry 213 (class 1259 OID 23298)
-- Name: spot; Type: TABLE; Schema: public; Owner: postgres
--

CREATE TABLE public.spot (
    id integer NOT NULL,
    city character varying(255),
    county character varying(255),
    image oid,
    name character varying(255),
    tagged boolean NOT NULL,
    climb_user_fk integer
);


ALTER TABLE public.spot OWNER TO postgres;

--
-- TOC entry 212 (class 1259 OID 23296)
-- Name: spot_id_seq; Type: SEQUENCE; Schema: public; Owner: postgres
--

CREATE SEQUENCE public.spot_id_seq
    AS integer
    START WITH 1
    INCREMENT BY 1
    NO MINVALUE
    NO MAXVALUE
    CACHE 1;


ALTER TABLE public.spot_id_seq OWNER TO postgres;

--
-- TOC entry 2936 (class 0 OID 0)
-- Dependencies: 212
-- Name: spot_id_seq; Type: SEQUENCE OWNED BY; Schema: public; Owner: postgres
--

ALTER SEQUENCE public.spot_id_seq OWNED BY public.spot.id;


--
-- TOC entry 215 (class 1259 OID 23309)
-- Name: topo; Type: TABLE; Schema: public; Owner: postgres
--

CREATE TABLE public.topo (
    id integer NOT NULL,
    available boolean NOT NULL,
    city character varying(255),
    country character varying(255),
    county character varying(255),
    date_release character varying(255),
    description character varying(600),
    image oid,
    name character varying(255),
    climb_user_fk integer
);


ALTER TABLE public.topo OWNER TO postgres;

--
-- TOC entry 214 (class 1259 OID 23307)
-- Name: topo_id_seq; Type: SEQUENCE; Schema: public; Owner: postgres
--

CREATE SEQUENCE public.topo_id_seq
    AS integer
    START WITH 1
    INCREMENT BY 1
    NO MINVALUE
    NO MAXVALUE
    CACHE 1;


ALTER TABLE public.topo_id_seq OWNER TO postgres;

--
-- TOC entry 2937 (class 0 OID 0)
-- Dependencies: 214
-- Name: topo_id_seq; Type: SEQUENCE OWNED BY; Schema: public; Owner: postgres
--

ALTER SEQUENCE public.topo_id_seq OWNED BY public.topo.id;


--
-- TOC entry 2740 (class 2604 OID 23228)
-- Name: climb_user id; Type: DEFAULT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY public.climb_user ALTER COLUMN id SET DEFAULT nextval('public.climb_user_id_seq'::regclass);


--
-- TOC entry 2741 (class 2604 OID 23239)
-- Name: comment id; Type: DEFAULT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY public.comment ALTER COLUMN id SET DEFAULT nextval('public.comment_id_seq'::regclass);


--
-- TOC entry 2742 (class 2604 OID 23250)
-- Name: grade id; Type: DEFAULT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY public.grade ALTER COLUMN id SET DEFAULT nextval('public.grade_id_seq'::regclass);


--
-- TOC entry 2743 (class 2604 OID 23258)
-- Name: length id; Type: DEFAULT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY public.length ALTER COLUMN id SET DEFAULT nextval('public.length_id_seq'::regclass);


--
-- TOC entry 2751 (class 2604 OID 23425)
-- Name: news_suscriber id; Type: DEFAULT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY public.news_suscriber ALTER COLUMN id SET DEFAULT nextval('public.news_suscriber_id_seq'::regclass);


--
-- TOC entry 2744 (class 2604 OID 23266)
-- Name: rating id; Type: DEFAULT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY public.rating ALTER COLUMN id SET DEFAULT nextval('public.rating_id_seq'::regclass);


--
-- TOC entry 2745 (class 2604 OID 23274)
-- Name: roles id; Type: DEFAULT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY public.roles ALTER COLUMN id SET DEFAULT nextval('public.roles_id_seq'::regclass);


--
-- TOC entry 2746 (class 2604 OID 23282)
-- Name: route id; Type: DEFAULT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY public.route ALTER COLUMN id SET DEFAULT nextval('public.route_id_seq'::regclass);


--
-- TOC entry 2747 (class 2604 OID 23290)
-- Name: sector id; Type: DEFAULT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY public.sector ALTER COLUMN id SET DEFAULT nextval('public.sector_id_seq'::regclass);


--
-- TOC entry 2748 (class 2604 OID 23301)
-- Name: spot id; Type: DEFAULT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY public.spot ALTER COLUMN id SET DEFAULT nextval('public.spot_id_seq'::regclass);


--
-- TOC entry 2749 (class 2604 OID 23312)
-- Name: topo id; Type: DEFAULT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY public.topo ALTER COLUMN id SET DEFAULT nextval('public.topo_id_seq'::regclass);


--
-- TOC entry 2753 (class 2606 OID 23233)
-- Name: climb_user climb_user_pkey; Type: CONSTRAINT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY public.climb_user
    ADD CONSTRAINT climb_user_pkey PRIMARY KEY (id);


--
-- TOC entry 2759 (class 2606 OID 23244)
-- Name: comment comment_pkey; Type: CONSTRAINT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY public.comment
    ADD CONSTRAINT comment_pkey PRIMARY KEY (id);


--
-- TOC entry 2779 (class 2606 OID 23397)
-- Name: flyway_schema_history flyway_schema_history_pk; Type: CONSTRAINT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY public.flyway_schema_history
    ADD CONSTRAINT flyway_schema_history_pk PRIMARY KEY (installed_rank);


--
-- TOC entry 2761 (class 2606 OID 23252)
-- Name: grade grade_pkey; Type: CONSTRAINT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY public.grade
    ADD CONSTRAINT grade_pkey PRIMARY KEY (id);


--
-- TOC entry 2763 (class 2606 OID 23260)
-- Name: length length_pkey; Type: CONSTRAINT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY public.length
    ADD CONSTRAINT length_pkey PRIMARY KEY (id);


--
-- TOC entry 2782 (class 2606 OID 23427)
-- Name: news_suscriber news_suscriber_pkey; Type: CONSTRAINT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY public.news_suscriber
    ADD CONSTRAINT news_suscriber_pkey PRIMARY KEY (id);


--
-- TOC entry 2765 (class 2606 OID 23268)
-- Name: rating rating_pkey; Type: CONSTRAINT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY public.rating
    ADD CONSTRAINT rating_pkey PRIMARY KEY (id);


--
-- TOC entry 2767 (class 2606 OID 23276)
-- Name: roles roles_pkey; Type: CONSTRAINT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY public.roles
    ADD CONSTRAINT roles_pkey PRIMARY KEY (id);


--
-- TOC entry 2769 (class 2606 OID 23284)
-- Name: route route_pkey; Type: CONSTRAINT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY public.route
    ADD CONSTRAINT route_pkey PRIMARY KEY (id);


--
-- TOC entry 2771 (class 2606 OID 23295)
-- Name: sector sector_pkey; Type: CONSTRAINT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY public.sector
    ADD CONSTRAINT sector_pkey PRIMARY KEY (id);


--
-- TOC entry 2773 (class 2606 OID 23306)
-- Name: spot spot_pkey; Type: CONSTRAINT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY public.spot
    ADD CONSTRAINT spot_pkey PRIMARY KEY (id);


--
-- TOC entry 2777 (class 2606 OID 23317)
-- Name: topo topo_pkey; Type: CONSTRAINT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY public.topo
    ADD CONSTRAINT topo_pkey PRIMARY KEY (id);


--
-- TOC entry 2775 (class 2606 OID 23323)
-- Name: spot uk_21cxfum5uawyvtqt7q4hfcrwg; Type: CONSTRAINT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY public.spot
    ADD CONSTRAINT uk_21cxfum5uawyvtqt7q4hfcrwg UNIQUE (name);


--
-- TOC entry 2755 (class 2606 OID 23321)
-- Name: climb_user uk_259xqh1a0tycqwlhsrf08x5b9; Type: CONSTRAINT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY public.climb_user
    ADD CONSTRAINT uk_259xqh1a0tycqwlhsrf08x5b9 UNIQUE (username);


--
-- TOC entry 2784 (class 2606 OID 23429)
-- Name: news_suscriber uk_irfxah867dvsmy6axfgl27dfm; Type: CONSTRAINT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY public.news_suscriber
    ADD CONSTRAINT uk_irfxah867dvsmy6axfgl27dfm UNIQUE (email);


--
-- TOC entry 2757 (class 2606 OID 23319)
-- Name: climb_user uk_lt9wjrwmn21xrglt767itfm9l; Type: CONSTRAINT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY public.climb_user
    ADD CONSTRAINT uk_lt9wjrwmn21xrglt767itfm9l UNIQUE (email);


--
-- TOC entry 2780 (class 1259 OID 23398)
-- Name: flyway_schema_history_s_idx; Type: INDEX; Schema: public; Owner: postgres
--

CREATE INDEX flyway_schema_history_s_idx ON public.flyway_schema_history USING btree (success);


--
-- TOC entry 2788 (class 2606 OID 23339)
-- Name: grade fk19blegh93xpa1ggxx21gu8fya; Type: FK CONSTRAINT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY public.grade
    ADD CONSTRAINT fk19blegh93xpa1ggxx21gu8fya FOREIGN KEY (rating_fk) REFERENCES public.rating(id);


--
-- TOC entry 2797 (class 2606 OID 23384)
-- Name: topo fk207osa3lrngisvhu8jy35ky2f; Type: FK CONSTRAINT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY public.topo
    ADD CONSTRAINT fk207osa3lrngisvhu8jy35ky2f FOREIGN KEY (climb_user_fk) REFERENCES public.climb_user(id);


--
-- TOC entry 2790 (class 2606 OID 23349)
-- Name: length fk2qfhonj4bq1r4nuokgwjte1s3; Type: FK CONSTRAINT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY public.length
    ADD CONSTRAINT fk2qfhonj4bq1r4nuokgwjte1s3 FOREIGN KEY (route_fk) REFERENCES public.route(id);


--
-- TOC entry 2793 (class 2606 OID 23364)
-- Name: route fk6f6hxn7priexdm5ojwenedf0q; Type: FK CONSTRAINT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY public.route
    ADD CONSTRAINT fk6f6hxn7priexdm5ojwenedf0q FOREIGN KEY (climb_user_fk) REFERENCES public.climb_user(id);


--
-- TOC entry 2794 (class 2606 OID 23369)
-- Name: sector fk7bwpl09h8vd7dfnl0p7v2d62o; Type: FK CONSTRAINT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY public.sector
    ADD CONSTRAINT fk7bwpl09h8vd7dfnl0p7v2d62o FOREIGN KEY (spot_fk) REFERENCES public.spot(id);


--
-- TOC entry 2792 (class 2606 OID 23359)
-- Name: route fk993olx7nui1t1rsnphtxbls1; Type: FK CONSTRAINT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY public.route
    ADD CONSTRAINT fk993olx7nui1t1rsnphtxbls1 FOREIGN KEY (sector_fk) REFERENCES public.sector(id);


--
-- TOC entry 2796 (class 2606 OID 23379)
-- Name: spot fke09ono2c6w0yfnc31m1rsjawj; Type: FK CONSTRAINT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY public.spot
    ADD CONSTRAINT fke09ono2c6w0yfnc31m1rsjawj FOREIGN KEY (climb_user_fk) REFERENCES public.climb_user(id);


--
-- TOC entry 2789 (class 2606 OID 23344)
-- Name: length fkeh5q10gdhwltjyhncjji3i4e9; Type: FK CONSTRAINT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY public.length
    ADD CONSTRAINT fkeh5q10gdhwltjyhncjji3i4e9 FOREIGN KEY (grade_fk) REFERENCES public.grade(id);


--
-- TOC entry 2787 (class 2606 OID 23334)
-- Name: comment fkg0bbpix35g6siwj4b4eccp8eh; Type: FK CONSTRAINT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY public.comment
    ADD CONSTRAINT fkg0bbpix35g6siwj4b4eccp8eh FOREIGN KEY (climb_user_fk) REFERENCES public.climb_user(id);


--
-- TOC entry 2795 (class 2606 OID 23374)
-- Name: sector fkj4rhmlsf56n7qlcnf0y6y66bb; Type: FK CONSTRAINT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY public.sector
    ADD CONSTRAINT fkj4rhmlsf56n7qlcnf0y6y66bb FOREIGN KEY (climb_user_fk) REFERENCES public.climb_user(id);


--
-- TOC entry 2786 (class 2606 OID 23329)
-- Name: comment fkr3vf3w6xnwnbh3exm3fmvlr4p; Type: FK CONSTRAINT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY public.comment
    ADD CONSTRAINT fkr3vf3w6xnwnbh3exm3fmvlr4p FOREIGN KEY (spot_fk) REFERENCES public.spot(id);


--
-- TOC entry 2791 (class 2606 OID 23354)
-- Name: length fkso0pm6ibnfr9silxqx3hrn3tc; Type: FK CONSTRAINT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY public.length
    ADD CONSTRAINT fkso0pm6ibnfr9silxqx3hrn3tc FOREIGN KEY (climb_user_fk) REFERENCES public.climb_user(id);


--
-- TOC entry 2785 (class 2606 OID 23324)
-- Name: climb_user fkt3yki5iuvkbf4fjic51weaasy; Type: FK CONSTRAINT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY public.climb_user
    ADD CONSTRAINT fkt3yki5iuvkbf4fjic51weaasy FOREIGN KEY (role_fk) REFERENCES public.roles(id);


-- Completed on 2019-07-11 19:41:36

--
-- PostgreSQL database dump complete
--

