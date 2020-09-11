-- phpMyAdmin SQL Dump
-- version 4.8.4
-- https://www.phpmyadmin.net/
--
-- Host: localhost
-- Waktu pembuatan: 07 Sep 2020 pada 10.00
-- Versi server: 10.1.37-MariaDB
-- Versi PHP: 7.3.0

SET SQL_MODE = "NO_AUTO_VALUE_ON_ZERO";
SET AUTOCOMMIT = 0;
START TRANSACTION;
SET time_zone = "+00:00";


/*!40101 SET @OLD_CHARACTER_SET_CLIENT=@@CHARACTER_SET_CLIENT */;
/*!40101 SET @OLD_CHARACTER_SET_RESULTS=@@CHARACTER_SET_RESULTS */;
/*!40101 SET @OLD_COLLATION_CONNECTION=@@COLLATION_CONNECTION */;
/*!40101 SET NAMES utf8mb4 */;

--
-- Database: `smkpublikdb`
--

-- --------------------------------------------------------

--
-- Struktur dari tabel `tabel_absen_siswa`
--

CREATE TABLE `tabel_absen_siswa` (
  `id` int(10) NOT NULL,
  `id_siswa` int(10) NOT NULL,
  `nama` varchar(255) NOT NULL,
  `kelas` varchar(10) NOT NULL,
  `jurusan` varchar(50) NOT NULL,
  `checkin` date NOT NULL,
  `checkout` date NOT NULL,
  `nisn` int(10) NOT NULL,
  `date` date NOT NULL,
  `latlong` double NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

-- --------------------------------------------------------

--
-- Struktur dari tabel `tabel_banner`
--

CREATE TABLE `tabel_banner` (
  `id` int(10) NOT NULL,
  `url` text NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

-- --------------------------------------------------------

--
-- Struktur dari tabel `tabel_guru`
--

CREATE TABLE `tabel_guru` (
  `id` int(11) NOT NULL,
  `username` varchar(50) NOT NULL,
  `nama` varchar(255) NOT NULL,
  `password` varchar(50) NOT NULL,
  `photo` text NOT NULL,
  `wali_kelas` varchar(50) NOT NULL,
  `nuptk` int(16) NOT NULL,
  `email` varchar(255) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

-- --------------------------------------------------------

--
-- Struktur dari tabel `tabel_materi`
--

CREATE TABLE `tabel_materi` (
  `id` int(11) NOT NULL,
  `judul` text NOT NULL,
  `deskripsi` text NOT NULL,
  `oleh` varchar(255) NOT NULL,
  `isi` text NOT NULL,
  `lampiran` text NOT NULL,
  `gambar` text NOT NULL,
  `tanggal` date NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

--
-- Dumping data untuk tabel `tabel_materi`
--

INSERT INTO `tabel_materi` (`id`, `judul`, `deskripsi`, `oleh`, `isi`, `lampiran`, `gambar`, `tanggal`) VALUES
(8, 'Memahami Struktur Text Anekdot', 'Belajar kembali mengenai text anekdot, apasih text anekdot itu?', 'hendri-bibil', 'Teks Anekdot adalah cerita singkat yang di dalamnya mengandung unsur lucu dan mempunyai maksud untuk melakukan kritikan. Teks anekdot biasanya bertopik tentang layanan publik, politik, lingkungan, dan sosial.', '', 'http://127.0.1.1/smkpublik/upload/images/cqysn0bvrf1ohnzo1hat.jpg', '2020-08-04'),
(9, 'Text Pantun', 'Mari Berpantun', 'hendri-bibil', 'Pantun adalah bentuk puisi lama yang terdiri atas empat larik, berima silang (a-b-a-b). Larik pertama dan kedua disebut sampiran. Larik ketiga dan keempat dinamakan isi', '', 'http://127.0.1.1/smkpublik/upload/images/cqysn0bvrf1ohnzo1hat.jpg', '2020-08-04');

-- --------------------------------------------------------

--
-- Struktur dari tabel `tabel_siswa`
--

CREATE TABLE `tabel_siswa` (
  `id` int(11) NOT NULL,
  `username` varchar(50) NOT NULL,
  `nama` varchar(255) NOT NULL,
  `password` varchar(50) NOT NULL,
  `photo` text NOT NULL,
  `kelas` varchar(10) NOT NULL,
  `jurusan` varchar(50) NOT NULL,
  `nisn` varchar(10) NOT NULL,
  `email` varchar(255) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

--
-- Dumping data untuk tabel `tabel_siswa`
--

INSERT INTO `tabel_siswa` (`id`, `username`, `nama`, `password`, `photo`, `kelas`, `jurusan`, `nisn`, `email`) VALUES
(9, 'gdev', 'HenBil Channel', 'e10adc3949ba59abbe56e057f20f883e', '-', '', 'Pemrograman', '1234567890', 'hendrijs44@gmail.com');

-- --------------------------------------------------------

--
-- Struktur dari tabel `tabel_user`
--

CREATE TABLE `tabel_user` (
  `id` int(11) NOT NULL,
  `username` varchar(50) NOT NULL,
  `nama` varchar(255) NOT NULL,
  `password` varchar(50) NOT NULL,
  `email` varchar(255) NOT NULL,
  `user_as` varchar(50) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

--
-- Dumping data untuk tabel `tabel_user`
--

INSERT INTO `tabel_user` (`id`, `username`, `nama`, `password`, `email`, `user_as`) VALUES
(7, 'hen98', 'hen98', '47cbe287d01676034b7a5b40c2727989', 'hendrijs44@gmail.com', 's'),
(8, 'aaa', 'aaa', 'd10906c3dac1172d4f60bd41f224ae75', 'hendrijs44@gmail.com', 's'),
(9, 'gdev', 'HenBil Channel', 'e10adc3949ba59abbe56e057f20f883e', 'hendrijs44@gmail.com', 's');

--
-- Indexes for dumped tables
--

--
-- Indeks untuk tabel `tabel_absen_siswa`
--
ALTER TABLE `tabel_absen_siswa`
  ADD PRIMARY KEY (`id`);

--
-- Indeks untuk tabel `tabel_banner`
--
ALTER TABLE `tabel_banner`
  ADD PRIMARY KEY (`id`);

--
-- Indeks untuk tabel `tabel_guru`
--
ALTER TABLE `tabel_guru`
  ADD PRIMARY KEY (`id`);

--
-- Indeks untuk tabel `tabel_materi`
--
ALTER TABLE `tabel_materi`
  ADD PRIMARY KEY (`id`);

--
-- Indeks untuk tabel `tabel_siswa`
--
ALTER TABLE `tabel_siswa`
  ADD PRIMARY KEY (`id`);

--
-- Indeks untuk tabel `tabel_user`
--
ALTER TABLE `tabel_user`
  ADD PRIMARY KEY (`id`);

--
-- AUTO_INCREMENT untuk tabel yang dibuang
--

--
-- AUTO_INCREMENT untuk tabel `tabel_absen_siswa`
--
ALTER TABLE `tabel_absen_siswa`
  MODIFY `id` int(10) NOT NULL AUTO_INCREMENT;

--
-- AUTO_INCREMENT untuk tabel `tabel_banner`
--
ALTER TABLE `tabel_banner`
  MODIFY `id` int(10) NOT NULL AUTO_INCREMENT;

--
-- AUTO_INCREMENT untuk tabel `tabel_guru`
--
ALTER TABLE `tabel_guru`
  MODIFY `id` int(11) NOT NULL AUTO_INCREMENT;

--
-- AUTO_INCREMENT untuk tabel `tabel_materi`
--
ALTER TABLE `tabel_materi`
  MODIFY `id` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=10;

--
-- AUTO_INCREMENT untuk tabel `tabel_siswa`
--
ALTER TABLE `tabel_siswa`
  MODIFY `id` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=10;

--
-- AUTO_INCREMENT untuk tabel `tabel_user`
--
ALTER TABLE `tabel_user`
  MODIFY `id` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=10;

--
-- Ketidakleluasaan untuk tabel pelimpahan (Dumped Tables)
--

--
-- Ketidakleluasaan untuk tabel `tabel_guru`
--
ALTER TABLE `tabel_guru`
  ADD CONSTRAINT `tabel_guru_ibfk_1` FOREIGN KEY (`id`) REFERENCES `tabel_user` (`id`);
COMMIT;

/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
